CREATE TABLE IF NOT EXISTS users (
    authUserId SERIAL PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    name_first VARCHAR(255),
    name_last VARCHAR(255),
    num_successful_logins INTEGER DEFAULT 0,
    num_failed_passwords_since_last_login INTEGER DEFAULT 0
);