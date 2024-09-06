USE jpa_audit_db;
CREATE TABLE IF NOT EXISTS history (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(25),
    operation   VARCHAR(25),
    date  TIMESTAMP
);