package org.springboot.samples.wiremock.extension;

import com.github.tomakehurst.wiremock.extension.StubLifecycleListener;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springboot.samples.wiremock.entity.WireMockStubEntity;
import org.springboot.samples.wiremock.repository.WiremockStubRepository;
import org.springframework.stereotype.Component;

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
        WireMockStubEntity entity = stubRepository.findById(stub.getId().toString())
                .orElse(new WireMockStubEntity());

        entity.setId(stub.getId().toString());
        entity.setName(stub.getName());
        entity.setStubJson(stub.toString());
        entity.setIsEnabled(true);

        stubRepository.save(entity);
        log.info("Synced stub change to DB: {}", stub.getName());
    }

    @Override
    public String getName() {
        return "stub-database-sync-listener";
    }
}
