--liquibase formatted sql
--changeset msalamon:2

CREATE TABLE EMPLOYEE
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(50)  NOT NULL UNIQUE ,
    email     VARCHAR(50) NOT NULL UNIQUE,
    password  VARCHAR(30)  NOT NULL
);


