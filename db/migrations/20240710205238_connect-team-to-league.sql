-- migrate:up
ALTER TABLE IF EXISTS team ADD COLUMN league_id BIGINT NOT NULL REFERENCES league (id);


-- migrate:down
ALTER TABLE IF EXISTS team DROP COLUMN league_id;
