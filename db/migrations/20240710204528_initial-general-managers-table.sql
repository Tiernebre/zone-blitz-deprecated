-- migrate:up
CREATE TABLE IF NOT EXISTS general_manager (
  id BIGSERIAL PRIMARY KEY NOT NULL
);


-- migrate:down
DROP TABLE IF EXISTS general_manager;
