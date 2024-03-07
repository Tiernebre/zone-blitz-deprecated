-- migrate:up
ALTER TABLE session ALTER COLUMN account_id SET NOT NULL;


-- migrate:down
ALTER TABLE session ALTER COLUMN account_id SET NULL;

