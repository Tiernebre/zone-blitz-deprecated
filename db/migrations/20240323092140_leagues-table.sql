-- migrate:up
CREATE TABLE IF NOT EXISTS league (
  id BIGSERIAL PRIMARY KEY NOT NULL,
  account_id BIGINT REFERENCES account (id) NOT NULL,
  name TEXT NOT NULL CHECK (char_length(name) > 0 AND char_length(name) <= 64)
);


-- migrate:down
DROP TABLE IF EXISTS league;

