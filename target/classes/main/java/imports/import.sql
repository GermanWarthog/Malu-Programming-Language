CREATE DATABASE IF NOT EXISTS malu;

CREATE TABLE IF NOT EXISTS malu_user (
    id INT NOT NULL AUTO_INCREMENT,
    user VARCHAR(64),
    password LONGTEXT,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS malu_projects (
    id INT NOT NULL AUTO_INCREMENT,
    owner VARCHAR(64),
    name VARCHAR(64),
    src LONGTEXT,
    PRIMARY KEY (id)
);