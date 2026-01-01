package org.springboot.samples.wiremock.extension;

import com.github.tomakehurst.wiremock.extension.StubLifecycleListener;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springboot.samples.wiremock.entity.WireMockStubEntity;
import org.springboot.samples.wiremock.repository.WiremockStubRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class StubToDatabaseSyncListener implements StubLifecycleListener {

    private final WiremockStubRepository stubRepository;

    @Override
    public void afterStubCreated(StubMapping stub) {
        saveOrUpdateDb(stub);
    }

    @Override
    public void afterStubEdited(StubMapping oldStub, StubMapping newStub) {
        saveOrUpdateDb(newStub);
    }

    @Override
    public void afterStubRemoved(StubMapping stub) {
        stubRepository.deleteById(stub.getId().toString());
    }

    private void saveOrUpdateDb(StubMapping stub) {
        String currentId = stub.getId().toString();
        if (!stub.isDirty()) {
            log.info("Stub [{}] is not dirty (possibly loaded from persistent storage). Skipping DB sync.", currentId);
            return;
        }

        Optional<WireMockStubEntity> existing = stubRepository.findById(currentId);

        if (existing.isPresent()) {
            log.info("Stub [{}] marked as dirty but content is identical to DB. Skipping update.", currentId);
            return;
        }

        WireMockStubEntity entity = existing.orElse(new WireMockStubEntity());
        String name = Optional.ofNullable(stub.getName())
                .filter(StringUtils::hasText)
                .orElseGet(() -> {
                    var request = stub.getRequest();
                    String path = Optional.ofNullable(request.getUrl())
                            .filter(StringUtils::hasText)
                            .orElseGet(() -> Optional.ofNullable(request.getUrlPath())
                                    .filter(StringUtils::hasText)
                                    .orElse("unspecified-path"));
                    return "Stub-" + request.getMethod() + " " + path;
                });
        entity.setId(stub.getId().toString());
        entity.setName(name);
        entity.setStubJson(stub.toString());
        entity.setIsEnabled(true);

        stubRepository.save(entity);
        log.info("Successfully synced changed stub [{}] to database.", currentId);
    }

    @Override
    public String getName() {
        return "stub-database-sync-listener";
    }
}
