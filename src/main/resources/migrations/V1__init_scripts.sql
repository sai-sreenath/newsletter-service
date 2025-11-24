CREATE TABLE IF NOT EXISTS topics (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description TEXT,
    created_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS subscribers (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS subscriber_topic (
    subscriber_id BIGINT REFERENCES subscribers(id),
    topic_id BIGINT REFERENCES topics(id),
    PRIMARY KEY (subscriber_id, topic_id)
);

CREATE TABLE IF NOT EXISTS contents (
    id BIGSERIAL PRIMARY KEY,
    topic_id BIGINT REFERENCES topics(id),
    subject VARCHAR(255) NOT NULL,
    body TEXT,
    scheduled_at TIMESTAMP NOT NULL,
    status VARCHAR(50),
    created_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS send_log (
    id BIGSERIAL PRIMARY KEY,
    content_id BIGINT REFERENCES contents(id),
    subscriber_id BIGINT REFERENCES subscribers(id),
    sent_at TIMESTAMP,
    status VARCHAR(50),
    error TEXT,
    provider_message_id VARCHAR(255)
);