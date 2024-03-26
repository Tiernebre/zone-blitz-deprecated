-- migrate:up
ALTER TABLE registration ADD CONSTRAINT username_non_blank CHECK (char_length(username) <= 64 AND char_length(username) > 0);


-- migrate:down

