CREATE
    DATABASE personal;
\c personal;
CREATE
    EXTENSION IF NOT EXISTS "uuid-ossp";

create table if not exists employees
(
    id     uuid default uuid_generate_v4() not null
        primary key,
    "name" text                            not null
);

alter table employees
    owner to postgres;

create type assignment_role as enum ('service', 'cleanup');

create table if not exists assignments
(
    id             uuid default uuid_generate_v4() not null
        primary key,
    employee_id    uuid                            not null
        references employees,
    reservation_id uuid                            not null,
    role           assignment_role                 not null
);

alter table assignments
    owner to postgres;
