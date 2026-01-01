package org.springboot.samples.wiremock.extension;

import com.github.tomakehurst.wiremock.extension.ResponseTransformerV2;
import com.github.tomakehurst.wiremock.http.HttpHeader;
import com.github.tomakehurst.wiremock.http.HttpHeaders;
import com.github.tomakehurst.wiremock.http.Response;
import com.github.tomakehurst.wiremock.stubbing.ServeEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springboot.samples.wiremock.entity.WireMockCacheEntity;
import org.springboot.samples.wiremock.repository.WireMockCacheRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
@Slf4j
public class StubCachingFallbackTransformer implements ResponseTransformerV2 {

    private final WireMockCacheRepository cacheRepository;

    @Override
    public Response transform(Response response, ServeEvent serveEvent) {
        if (!response.isFromProxy()) {
            return response;
        }

        var request = serveEvent.getRequest();
        String url = request.getUrl();
        String method = request.getMethod().getName();

        if (HttpStatus.valueOf(response.getStatus()).is2xxSuccessful()) {
            log.info("Proxy request successful for [{} {}]. Triggering async cache update.", method, url);
            asyncSave(url, method, response);
            return response;
        }

        log.warn("Proxy request failed with status [{}] for [{} {}]. Searching for cached fallback.", response.getStatus(), method, url);
        WireMockCacheEntity cachedData = cacheRepository.findByRequestUrlAndRequestMethod(url, method);
        if (cachedData != null) {
            return buildCachedResponse(response, cachedData);
        }

        return response;
    }

    @Override
    public boolean applyGlobally() {
        return true;
    }

    @Override
    public String getName() {
        return "stub-cache-fallback";
    }

    private void asyncSave(String url, String method, Response response) {
        String body = response.getBodyAsString();
        String contentType = response.getHeaders().getHeader("Content-Type").firstValue();
        int code = response.getStatus();

        CompletableFuture.runAsync(() -> {
            try {
                WireMockCacheEntity entity = cacheRepository.findByRequestUrlAndRequestMethod(url, method);
                if (entity == null) {
                    entity = new WireMockCacheEntity();
                    entity.setRequestUrl(url);
                    entity.setRequestMethod(method);
                }
                entity.setResponseBody(body);
                entity.setContentType(contentType != null ? contentType : "application/json");
                entity.setStatus(code);
                cacheRepository.save(entity);
            } catch (Exception e) {
                log.error("Failed to save WireMock cache for URL: {}, Error: {}", url, e.getMessage(), e);
            }
        });
    }

    private Response buildCachedResponse(Response originalResponse, WireMockCacheEntity cachedData) {
        return Response.Builder.like(originalResponse)
                .status(HttpStatus.OK.value())
                .body(cachedData.getResponseBody())
                .headers(new HttpHeaders(new HttpHeader("Content-Type", cachedData.getContentType())))
                .build();
    }
}
