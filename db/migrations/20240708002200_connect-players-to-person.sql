-- migrate:up
ALTER TABLE player ADD person_id BIGINT UNIQUE REFERENCES person (id);


-- migrate:down
ALTER TABLE player DROP COLUMN person_id;
