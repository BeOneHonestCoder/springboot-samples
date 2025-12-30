package org.springboot.samples.wiremock.extension;

import com.github.tomakehurst.wiremock.common.Metadata;
import com.github.tomakehurst.wiremock.extension.MappingsLoaderExtension;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import com.github.tomakehurst.wiremock.stubbing.StubMappings;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springboot.samples.wiremock.repository.WiremockStubRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class DatabaseMappingsLoader implements MappingsLoaderExtension {

    private final WiremockStubRepository repository;

    @Override
    public void loadMappingsInto(StubMappings stubMappings) {
        repository.findByIsEnabledTrue().forEach(stub -> {
            StubMapping mapping = StubMapping.buildFrom(stub.getStubJson());
            mapping.setId(UUID.fromString(stub.getId()));
            mapping.setMetadata(Metadata.metadata()
                    .attr("id", stub.getId())
                    .attr("source", "database")
                    .build());
            stubMappings.addMapping(mapping);
        });
    }

    @Override
    public String getName() {
        return "database-mappings-loader";
    }
}
