-- migrate:up
CREATE TABLE IF NOT EXISTS registration (
  id BIGSERIAL PRIMARY KEY NOT NULL,
  username TEXT NOT NULL UNIQUE,
  password TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS account (
  id BIGSERIAL PRIMARY KEY NOT NULL,
  registration_id BIGINT NULL REFERENCES registration (id),
  google_account_id TEXT NULL
);

ALTER TABLE IF EXISTS session DROP COLUMN account_id;
ALTER TABLE IF EXISTS session ADD COLUMN account_id BIGINT REFERENCES account(id);


-- migrate:down
ALTER TABLE IF EXISTS session DROP column account_id;
ALTER TABLE IF EXISTS session ADD COLUMN account_id TEXT;

DROP TABLE IF EXISTS account;

DROP TABLE IF EXISTS registration;

