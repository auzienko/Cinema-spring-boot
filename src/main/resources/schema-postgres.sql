drop schema if exists cinema cascade ;
create schema if not exists cinema;



CREATE TABLE IF NOT EXISTS cinema.cinema_users
(
    id               BIGSERIAL PRIMARY KEY,
    username         VARCHAR,
    email            VARCHAR,
    password         VARCHAR,
    role             INTEGER,
    status           INTEGER
);

-- CREATE TABLE IF NOT EXISTS cinema.administrators
-- (
--     id               BIGSERIAL PRIMARY KEY,
--     email            VARCHAR,
--     password         VARCHAR
-- );

CREATE TABLE IF NOT EXISTS cinema.posters
(
    id               BIGSERIAL PRIMARY KEY,
    file_name        VARCHAR,
    size             BIGINT,
    mime             VARCHAR,
    file_name_UUID   VARCHAR,
    administrator_id BIGINT
);

CREATE TABLE IF NOT EXISTS cinema.movies
(
    id               BIGSERIAL PRIMARY KEY,
    title            VARCHAR,
    year_of_release  INTEGER,
    age_restriction  INTEGER,
    description      VARCHAR,
    poster_id        BIGINT,
    administrator_id BIGINT
);

CREATE TABLE IF NOT EXISTS cinema.movie_halls
(
    id               BIGSERIAL PRIMARY KEY,
    serial_number    INTEGER,
    seats            INTEGER,
    administrator_id BIGINT
);

CREATE TABLE IF NOT EXISTS cinema.sessions
(
    id               BIGSERIAL PRIMARY KEY,
    movie_id         BIGINT,
    date_time        TIMESTAMP,
    cost             INTEGER,
    movie_hall_id    BIGINT,
    administrator_id BIGINT
);

CREATE TABLE IF NOT EXISTS cinema.email_confirmations
(
    id               BIGSERIAL PRIMARY KEY,
    token            VARCHAR,
    user_id          BIGINT
);