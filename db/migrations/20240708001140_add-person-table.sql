-- migrate:up
CREATE TABLE IF NOT EXISTS player (
  id BIGSERIAL PRIMARY KEY NOT NULL
);

-- migrate:down
DROP TABLE IF EXISTS player;
