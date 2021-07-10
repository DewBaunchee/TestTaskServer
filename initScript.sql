/*
DROP TABLE sensor;
DROP TABLE role;
DROP TABLE user;
DROP TABLE user_roles;
*/

CREATE TABLE sensor
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name        VARCHAR(30)                       NOT NULL,
    model       VARCHAR(15)                       NOT NULL,
    range_from  INT,
    range_to    INT,
    sensor_type enum ('Pressure', 'Voltage', 'Temperature', 'Humidity'),
    unit_type   enum ('bar', 'voltage', '℃', '%'),
    location    VARCHAR(40),
    description VARCHAR(200)
);
CREATE TABLE role
(
    id   BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(30) UNIQUE                NOT NULL
);

CREATE TABLE user
(
    id       BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    username VARCHAR(64) UNIQUE                NOT NULL,
    password VARCHAR(64)                       NOT NULL
);

CREATE TABLE user_roles
(
    user_id bigint NOT NULL,
    role_id bigint NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (role_id) REFERENCES role (id) ON DELETE RESTRICT ON UPDATE CASCADE,
    PRIMARY KEY (user_id, role_id)
);

INSERT INTO sensor
VALUES (NULL, 'Sensor 1', 'PC33-56', 0, 16, 'Pressure', 'bar', 'Room 1', 'Description 1'),
       (NULL, 'Sensor 2', 'EH-567', -25, 25, 'Voltage', 'voltage', 'Room 1', 'Description 2'),
       (NULL, 'Sensor 3', 'TER-21', -70, 50, 'Temperature', '℃', 'Room 2', 'Description 3'),
       (NULL, 'Sensor 4', 'H94', 0, 100, 'Humidity', '%', 'Room 3', 'Description 4'),
       (NULL, 'Sensor 5', 'PC33-56', 0, 16, 'Pressure', 'bar', 'Room 1', 'Description 5'),
       (NULL, 'Sensor 6', 'EH-567', -25, 25, 'Voltage', 'voltage', 'Room 1', 'Description 6'),
       (NULL, 'Sensor 7', 'TER-21', -70, 50, 'Temperature', '℃', 'Room 2', 'Description 7'),
       (NULL, 'Sensor 8', 'H94', 0, 100, 'Humidity', '%', 'Room 3', 'Description 8'),
       (NULL, 'Sensor 9', 'PC33-56', 0, 16, 'Pressure', 'bar', 'Room 1', 'Description 9'),
       (NULL, 'Sensor 10', 'EH-567', -25, 25, 'Voltage', 'voltage', 'Room 1', 'Description 10'),
       (NULL, 'Sensor 11', 'TER-21', -70, 50, 'Temperature', '℃', 'Room 2', 'Description 11'),
       (NULL, 'Sensor 12', 'H94', 0, 100, 'Humidity', '%', 'Room 3', 'Description 12');
