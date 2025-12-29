package org.springboot.samples.wiremock.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "wiremock_stubs")
@Getter
@Setter
public class WireMockStubEntity {

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(name = "stub_json", columnDefinition = "json", nullable = false)
    private String stubJson;

    @Column(name = "is_enabled")
    private Boolean isEnabled = true;

    @Column(name = "last_modified_date", insertable = false, updatable = false)
    private LocalDateTime lastModifiedDate;

}
