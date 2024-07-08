-- migrate:up
ALTER TABLE person ADD first_name TEXT NOT NULL;
ALTER TABLE person ADD last_name TEXT NOT NULL;


-- migrate:down
ALTER TABLE person DROP COLUMN first_name;
ALTER TABLE person DROP COLUMN last_name;
