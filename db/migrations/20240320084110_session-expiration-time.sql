-- migrate:up
ALTER TABLE session ADD COLUMN expires_at TIMESTAMP NOT NULL DEFAULT now() + '1 hour'::interval;


-- migrate:down
ALTER TABLE session DROP COLUMN expires_at;

