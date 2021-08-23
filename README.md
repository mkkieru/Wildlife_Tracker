Wildlife-Tracker2 APP, August 20 2021 By Mark kieru

Description

This is a java based application that allows park rangers to keep a well organized record of the animals in their park.
It also keeps track of details such as the animals age, health, location animal was spotted and given name.

Technologies Used 

Java
Spark.Java
Gradle
Maven

Setup Instructions

For You to have a copy of this application, fork and clone this repository [https://github.com/mkkieru/Wildlife_Tracker.git] into your local machine.
Then run the create.sql file using psql<create.sql to setup the postgress databases.

Known Bugs 

There are no known bugs currently. The page works as expected. 
Support and Contact Details For any comments,suggestions,feedback or inquiries, contact me via email:[mkkieru55@gmail.com]

Database Tables

This is the database structure of the Wildlife-Tracher application inclusive of test database.

CREATE DATABASE wildlife_tracker;
\c wildlife_tracker;
CREATE TABLE animals (
        id SERIAL PRIMARY KEY,
        name varchar,
        species VARCHAR,
        status varchar,
        health varchar,
        age varchar,
        locationID varchar
);

CREATE TABLE sightings (id SERIAL PRIMARY KEY, location VARCHAR);
CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;

#postgresql-rugged-71171
License This project is licensed under the MIT Open Source license Copyright (c) 2021. [Mark Kieru]