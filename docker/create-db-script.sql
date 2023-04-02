------------------------------------------------------------------------------
--          data base schema for the dashboard application                  --
------------------------------------------------------------------------------

-- this is the schema
create schema if not exists demo;

-- person table. this is to keep track of the information linked to a person.
create table demo.person
(
    id       uuid unique         not null,
    username varchar(255) unique not null,
    role     varchar(255) unique not null,
    primary key (id),
    unique (username, password)
);

insert into demo.person(id, username, role)
VALUES ('b6e38a2a-334d-4ed2-8f05-b1ca03e9397e', 'person', 'user'),
       ('e2143053-0d87-4a7f-9d4e-60e0ea52e4ff', 'member', 'member'),
       ('97abd3b4-b339-4a50-8cb6-06d71e85588c', 'admin', 'admin'),