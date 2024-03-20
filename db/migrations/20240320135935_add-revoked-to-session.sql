-- migrate:up
ALTER TABLE session ADD COLUMN revoked BOOL NOT NULL DEFAULT false;


-- migrate:down
ALTER TABLE session DROP COLUMN revoked;

