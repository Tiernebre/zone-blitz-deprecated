-- migrate:up
ALTER TABLE registration ADD CONSTRAINT username_length CHECK (char_length(username) <= 64 AND char_length(username) > 0);


-- migrate:down
ALTER TABLE registration DROP CONSTRAINT username_length;

