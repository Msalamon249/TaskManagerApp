--liquibase formatted sql
--changeset msalamon:3

CREATE TABLE TASK
(
    ID          BIGINT AUTO_INCREMENT PRIMARY KEY,
    TITLE       VARCHAR(50)  NOT NULL,
    DESCRIPTION VARCHAR(500) NOT NULL,
    PRIORITY    VARCHAR(20)  NOT NULL,
    END_DATE    DATE,
    EMPLOYEE_ID BIGINT,
    FOREIGN KEY (EMPLOYEE_ID) REFERENCES EMPLOYEE (ID),
    CATEGORY_ID BIGINT       NOT NULL,
    FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORY (ID)
);


