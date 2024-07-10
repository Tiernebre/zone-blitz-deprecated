-- migrate:up
CREATE TABLE IF NOT EXISTS team (
  id BIGSERIAL PRIMARY KEY NOT NULL
);


-- migrate:down
DROP TABLE IF EXISTS team;
