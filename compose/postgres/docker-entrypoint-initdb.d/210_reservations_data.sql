-- TODO: migrate to script and get user/pass from env
\c reservations;
CREATE
    EXTENSION IF NOT EXISTS "postgres_fdw";
CREATE SERVER assets
    FOREIGN DATA WRAPPER postgres_fdw
    OPTIONS (dbname 'assets');
CREATE USER MAPPING FOR postgres
    SERVER assets
    OPTIONS (user 'postgres', password 'postgres');
CREATE FOREIGN TABLE rooms
    (
        id uuid default uuid_generate_v4() not null,
        "name" text not null,
        storey_id uuid not null
        )
    SERVER assets
    OPTIONS (schema_name 'public', table_name 'rooms');


INSERT INTO reservations ("from", "to", room_id)
VALUES ('2022-03-10', '2022-03-11', (SELECT id FROM rooms WHERE name = '003')),
       ('2022-03-01', '2022-03-10', (SELECT id FROM rooms WHERE name = '003')),
       ('2022-04-01', '2022-04-15', (SELECT id FROM rooms WHERE name = '210'));
