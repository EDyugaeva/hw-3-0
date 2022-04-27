--liquibase formatted sql

--changeSet edyugaeva: 1
    CREATE INDEX student_name_index ON student (name);

--changeSet edyugaeva: 2
    CREATE INDEX faculty_name_colour_index ON faculty (name, colour);

