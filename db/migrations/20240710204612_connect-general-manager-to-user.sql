-- migrate:up
ALTER TABLE IF EXISTS general_manager ADD COLUMN user_id BIGINT REFERENCES account (id);


-- migrate:down
ALTER TABLE IF EXISTS general_manager DROP COLUMN user_id;
