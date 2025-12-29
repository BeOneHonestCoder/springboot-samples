DROP TABLE IF EXISTS `wiremock_stubs`;
DROP TABLE IF EXISTS `wiremock_cache`;

CREATE TABLE wiremock_stubs (
    id VARCHAR(36) PRIMARY KEY COMMENT '建议使用UUID，与WireMock内部ID保持一致',
    name VARCHAR(255) NOT NULL COMMENT 'Stub的描述名称',
    stub_json JSON NOT NULL COMMENT '完整的WireMock StubMapping JSON定义',
    is_enabled BOOLEAN DEFAULT TRUE COMMENT '是否启用该Mock',
    last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    INDEX idx_is_enabled (is_enabled)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE wiremock_cache (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    request_method VARCHAR(10) NOT NULL COMMENT 'GET, POST等',
    request_url VARCHAR(2000) NOT NULL COMMENT '完整的请求路径',
    response_body LONGTEXT COMMENT '核心返回数据',
    content_type VARCHAR(100) COMMENT '如 application/json',
    status_code INT NOT NULL COMMENT 'HTTP状态码',
    captured_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '捕获时间',

    -- 索引：方便在 1000+ 记录中按 URL 快速搜索
    INDEX idx_url (request_url(255)),
    INDEX idx_time (captured_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;