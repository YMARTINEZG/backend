CREATE TABLE customers (
  id             BIGINT PRIMARY KEY,
  first_name     VARCHAR,
  last_name      VARCHAR,
  address        VARCHAR,
  email          VARCHAR
);

CREATE SEQUENCE customer_seq START WITH 1000;
CREATE TABLE notes (
  id               BIGINT PRIMARY KEY,
  text             VARCHAR,
  creation_instant TIMESTAMPTZ NOT NULL
);

CREATE SEQUENCE note_seq START WITH 1000;

CREATE TABLE customer_note (
  customer_id BIGINT,
  note_id   BIGINT,
  PRIMARY KEY (customer_id, note_id)
);

ALTER TABLE customer_note ADD CONSTRAINT customer_note_fk1 FOREIGN KEY (customer_id) REFERENCES customers (id);
ALTER TABLE customer_note ADD CONSTRAINT customer_note_fk2 FOREIGN KEY (note_id) REFERENCES notes (id);
