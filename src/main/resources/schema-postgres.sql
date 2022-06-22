CREATE TABLE IF NOT EXISTS cinema_users
(
    id               BIGSERIAL PRIMARY KEY,
    username         VARCHAR,
    email            VARCHAR,
    password         VARCHAR,
    role             INTEGER,
    status           INTEGER
);

CREATE TABLE IF NOT EXISTS administrators
(
    id               BIGSERIAL PRIMARY KEY,
    email            VARCHAR,
    password         VARCHAR
);

CREATE TABLE IF NOT EXISTS posters
(
    id               BIGSERIAL PRIMARY KEY,
    file_name        VARCHAR,
    size             BIGINT,
    mime             VARCHAR,
    file_name_UUID   VARCHAR,
    administrator_id BIGINT
);

CREATE TABLE IF NOT EXISTS movies
(
    id               BIGSERIAL PRIMARY KEY,
    title            VARCHAR,
    year_of_release  INTEGER,
    age_restriction  INTEGER,
    description      VARCHAR,
    poster_id        BIGINT,
    administrator_id BIGINT
);

CREATE TABLE IF NOT EXISTS movie_halls
(
    id               BIGSERIAL PRIMARY KEY,
    serial_number    INTEGER,
    seats            INTEGER,
    administrator_id BIGINT
);

CREATE TABLE IF NOT EXISTS sessions
(
    id               BIGSERIAL PRIMARY KEY,
    movie_id         BIGINT,
    date_time        TIMESTAMP,
    cost             INTEGER,
    movie_hall_id    BIGINT,
    administrator_id BIGINT
);

CREATE TABLE IF NOT EXISTS email_confirmations
(
    id               BIGSERIAL PRIMARY KEY,
    token            VARCHAR,
    user_id          BIGINT
);