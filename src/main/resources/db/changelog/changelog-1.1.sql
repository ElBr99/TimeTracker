--liquibase formatted sql

----changeset elbr:0
 create schema  if not exists public;

--changeset elbr:1

  create table if not exists public.users (
  id serial primary key,
  head_id bigint references users(id),
  name varchar (255) not null,
  last_name varchar (255) not null,
  role varchar (255) not null
  );


--changeset elbr:2

  create table if not exists public.tasks (
  id serial primary key,
  name varchar (255) not null,
  description varchar (255) not null,
  user_id bigint references users(id) not null
  );

--changeset elbr:3
create table if not exists task_history (
    id serial primary key,
    task_id bigint references tasks(id) not null,
    started_at timestamptz,
    finished_at timestamptz,
    status varchar (255) not null
    );

