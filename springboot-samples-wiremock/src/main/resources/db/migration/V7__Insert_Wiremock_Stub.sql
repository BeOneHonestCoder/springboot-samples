-- 修复 JSON 格式，确保内部双引号转义正确且无多余换行
INSERT INTO wiremock_stubs (
    id,
    name,
    stub_json,
    is_enabled
) VALUES (
    '550e8400-e29b-41d4-a716-446655440000',
    'Database Test Stub',
    '{"request":{"method":"GET","url":"/api/db-test"},"response":{"status":200,"body":"{\\"message\\": \\"Hello! This data is automatically inserted by Flyway and loaded from MySQL!\\", \\"success\\": true}","headers":{"Content-Type":"application/json","X-Source":"Flyway-V2"}}}',
    1
);