-- migrate:up
ALTER TABLE registration DROP COLUMN password;
ALTER TABLE registration ADD COLUMN password BYTEA NOT NULL;


-- migrate:down
ALTER TABLE registration DROP COLUMN password;
ALTER TABLE registration ADD COLUMN password TEXT NOT NULL;

