package org.springboot.samples.wiremock.repository;

import org.springboot.samples.wiremock.entity.WireMockStubEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WiremockStubRepository extends JpaRepository<WireMockStubEntity, String> {

    List<WireMockStubEntity> findByIsEnabledTrue();
}
