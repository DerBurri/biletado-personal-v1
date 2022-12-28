-- TODO: migrate to script and get user/pass from env
\c personal;

INSERT INTO employees ("name")
VALUES ('Veki Purigisto'),
       ('Mirinda Purigistino'),
       ('Venka Servistino');

CREATE
    EXTENSION IF NOT EXISTS "postgres_fdw";
CREATE SERVER reservations
    FOREIGN DATA WRAPPER postgres_fdw
    OPTIONS (dbname 'reservations');
CREATE USER MAPPING FOR postgres
    SERVER reservations
    OPTIONS (user 'postgres', password 'postgres');
CREATE FOREIGN TABLE reservations
    (
        id uuid default uuid_generate_v4() not null,
        "from" date not null,
        "to" date not null,
        room_id uuid not null
        )
    SERVER reservations
    OPTIONS (schema_name 'public', table_name 'reservations');

-- reservation from 2022-03-10 has no service assigned.
INSERT INTO assignments (employee_id, reservation_id, role)
VALUES ((SELECT id FROM employees WHERE "name" = 'Veki Purigisto'), (SELECT id FROM reservations WHERE "from" = '2022-03-10'), 'cleanup'),
       ((SELECT id FROM employees WHERE "name" = 'Veki Purigisto'), (SELECT id FROM reservations WHERE "from" = '2022-03-01'), 'cleanup'),
       ((SELECT id FROM employees WHERE "name" = 'Mirinda Purigistino'), (SELECT id FROM reservations WHERE "from" = '2022-04-01'), 'cleanup'),
       ((SELECT id FROM employees WHERE "name" = 'Venka Servistino'), (SELECT id FROM reservations WHERE "from" = '2022-03-01'), 'service'),
       ((SELECT id FROM employees WHERE "name" = 'Venka Servistino'), (SELECT id FROM reservations WHERE "from" = '2022-04-01'), 'service');
