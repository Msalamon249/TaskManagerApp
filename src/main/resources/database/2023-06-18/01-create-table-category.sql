--liquibase formatted sql
--changeset msalamon:1

CREATE TABLE CATEGORY
(
    ID          BIGINT AUTO_INCREMENT PRIMARY KEY,
    NAME        VARCHAR(50) NOT NULL ,
    DESCRIPTION VARCHAR(500) NOT NULL
);
