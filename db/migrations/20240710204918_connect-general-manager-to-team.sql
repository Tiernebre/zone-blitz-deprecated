-- migrate:up
ALTER TABLE IF EXISTS general_manager ADD COLUMN team_id BIGINT NOT NULL REFERENCES team (id);


-- migrate:down
ALTER TABLE IF EXISTS general_manager DROP COLUMN team_id;
