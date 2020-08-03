BEGIN;

DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users (id bigserial PRIMARY KEY, name VARCHAR(255));
INSERT INTO users (name) VALUES
('Alexander'),
('Bob'),
('Jon'),
('Bob'),
('Kol'),
('Tim'),
('Tom'),
('Yn');


DROP TABLE IF EXISTS items CASCADE;
CREATE TABLE items (id bigserial PRIMARY KEY, title VARCHAR(255) , bid bigint , user_id bigint references users (id), version int);
INSERT INTO items (title, bid ,user_id, version) VALUES
('Book', 0, 1, 0),
('Laptop', 0, 2, 0),
('Phone', 0, 4, 0),
('Table', 0, 6, 0);

COMMIT;