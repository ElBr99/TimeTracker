--liquibase formatted sql

--changeset elbr:0

  alter table tasks
  alter column description
  drop not null;