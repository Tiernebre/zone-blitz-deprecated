-- migrate:up
ALTER TABLE account ADD CONSTRAINT unique_registration_id UNIQUE (registration_id);
ALTER TABLE account ADD CONSTRAINT unique_google_account_id UNIQUE (google_account_id);


-- migrate:down
ALTER TABLE account DROP CONSTRAINT unique_google_account_id;
ALTER TABLE account DROP CONSTRAINT unique_registration_id;

