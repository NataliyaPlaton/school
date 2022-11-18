-- liquibase formatted sql

-- changeset Natalie:1
CREATE INDEX student_name_index ON student (name);

-- changeset Natalie:2
CREATE INDEX faculty_nc_idx ON faculty (name, color);