--liquibase formatted sql

--changeset elbr:0

  alter table users
  alter column role
  drop not null;

