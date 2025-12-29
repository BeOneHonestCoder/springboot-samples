package org.springboot.samples.wiremock.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "wiremock_cache")
@Getter
@Setter
public class WireMockCacheEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "request_method", nullable = false)
    private String requestMethod;

    @Column(name = "request_url", length = 2000, nullable = false)
    private String requestUrl;

    @Column(name = "response_body", columnDefinition = "LONGTEXT", nullable = false)
    private String responseBody;

    @Column(name = "content_type", nullable = false)
    private String contentType;

    @Column(name = "status_code", nullable = false)
    private Integer status;

    @Column(name = "captured_at", insertable = false, updatable = false)
    private LocalDateTime capturedAt;
}
