-- migrate:up
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE TABLE IF NOT EXISTS session (
  id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  account_id TEXT
);


-- migrate:down
DROP TABLE IF EXISTS session;
DROP EXTENSION IF EXISTS "uuid-ossp";

