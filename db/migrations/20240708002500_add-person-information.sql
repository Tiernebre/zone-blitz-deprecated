-- migrate:up
ALTER TABLE person ADD first_name TEXT NOT NULL;


-- migrate:down
