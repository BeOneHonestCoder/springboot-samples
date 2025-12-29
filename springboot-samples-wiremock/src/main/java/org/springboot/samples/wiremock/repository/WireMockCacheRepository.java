package org.springboot.samples.wiremock.repository;

import org.springboot.samples.wiremock.entity.WireMockCacheEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WireMockCacheRepository extends JpaRepository<WireMockCacheEntity, Long> {

    boolean existsByRequestUrl(String requestUrl);

    WireMockCacheEntity findByRequestUrl(String requestUrl);
}
