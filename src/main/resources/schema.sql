DROP TABLE IF EXISTS Member;

create table Member
(
    id integer not null,
    name varchar(255) not null,
    primary key (id)
);

CREATE TABLE lg_url
(
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     original_url VARCHAR(255) NOT NULL UNIQUE,
     short_url VARCHAR(8) NOT NULL UNIQUE,
     request_count INT DEFAULT 0
);