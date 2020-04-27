CREATE TABLE income_source (
                               id                 BIGINT PRIMARY KEY,
                               name               VARCHAR NOT NULL,
                               max_monthly_amount NUMERIC(10, 2)
);

CREATE SEQUENCE income_source_seq START WITH 1000;

CREATE TABLE income (
                        id             BIGINT PRIMARY KEY,
                        customer_id    BIGINT         NOT NULL,
                        source_id      BIGINT         NOT NULL,
                        monthly_amount NUMERIC(10, 2) NOT NULL
);

ALTER TABLE income
    ADD CONSTRAINT income_fk1 FOREIGN KEY (customer_id) REFERENCES customer (id);
ALTER TABLE income
    ADD CONSTRAINT income_fk2 FOREIGN KEY (source_id) REFERENCES income_source (id);

CREATE SEQUENCE income_seq START WITH 1000;
