-- migrate:up
CREATE TABLE IF NOT EXISTS coach (
  id BIGSERIAL PRIMARY KEY NOT NULL
);


-- migrate:down
DROP TABLE IF EXISTS coach;
