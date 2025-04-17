--liquibase formatted sql

--changeset elbr:0

  alter table tasks
  alter column name
  drop not null;

--changeset elbr:1
  alter table tasks
  alter column status
  drop not null;

