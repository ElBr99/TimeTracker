--liquibase formatted sql

--changeset elbr:0

  alter table tasks
  alter column user_id
  drop not null;