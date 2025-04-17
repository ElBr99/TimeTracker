--liquibase formatted sql

--changeset elbr:0

  alter table public.tasks
  add column started_at timestamptz,
  add column finished_at timestamptz,
  add column status varchar (255) not null;

--changeset elbr:1
  DROP table public.task_history



