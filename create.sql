CREATE DATABASE wildlife_tracker;
\c wildlife_tracker;
CREATE TABLE animals (
        id SERIAL PRIMARY KEY,
        name varchar,
        species VARCHAR,
        status varchar,
        health varchar,
        age varchar
);

CREATE TABLE sightings (id SERIAL PRIMARY KEY, location VARCHAR);

CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;