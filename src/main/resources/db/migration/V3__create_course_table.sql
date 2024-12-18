CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE course (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    course_category_id SMALLINT NOT NULL,
    active BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (course_category_id) REFERENCES course_category(id) ON DELETE CASCADE
);