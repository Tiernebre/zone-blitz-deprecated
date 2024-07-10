-- migrate:up
CREATE TABLE IF NOT EXISTS team_brand (
  id BIGSERIAL PRIMARY KEY NOT NULL
);


-- migrate:down
DROP TABLE IF EXISTS team_brand;
