-- migrate:up
CREATE TABLE IF NOT EXISTS person (
  id BIGSERIAL PRIMARY KEY NOT NULL
);


-- migrate:down
DROP TABLE IF EXISTS person;
