CREATE
    DATABASE reservations;
\c reservations;
CREATE
    EXTENSION IF NOT EXISTS "uuid-ossp";

create table if not exists reservations
(
    id      uuid default uuid_generate_v4() not null
        primary key,
    "from"  date                            not null,
    "to"    date                            not null,
    room_id uuid                            not null
);

alter table reservations
    owner to postgres;
