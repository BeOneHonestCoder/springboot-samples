package org.springboot.samples.wiremock.tasks;

import com.github.tomakehurst.wiremock.admin.AdminTask;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.common.url.PathParams;
import com.github.tomakehurst.wiremock.core.Admin;
import com.github.tomakehurst.wiremock.http.ResponseDefinition;
import com.github.tomakehurst.wiremock.stubbing.ServeEvent;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springboot.samples.wiremock.repository.WiremockStubRepository;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class SearchRequestsTask implements AdminTask {

    private final WiremockStubRepository stubRepository;

    @Override
    public ResponseDefinition execute(Admin admin, ServeEvent serveEvent, PathParams pathParams) {
        // You can add more query parameters
        String idQuery = serveEvent.getRequest().queryParameter("id").firstValue();

        List<StubMapping> matched = stubRepository.findById(idQuery)
                .map(stub -> List.of(StubMapping.buildFrom(stub.getStubJson())))
                .orElse(Collections.emptyList());

        Map<String, Object> result = Map.of("mappings", matched);

        return ResponseDefinitionBuilder.jsonResponse(result);
    }
}
