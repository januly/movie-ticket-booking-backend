CREATE DATABASE IF NOT EXISTS movie_booking CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS 'movie_booking'@'localhost' IDENTIFIED BY 'userpassword123';
ALTER USER 'movie_booking'@'localhost' IDENTIFIED BY 'userpassword123';
GRANT ALL PRIVILEGES ON movie_booking.* TO 'movie_booking'@'localhost';

USE movie_booking;
SET time_zone = '+05:30';

CREATE TABLE IF NOT EXISTS users (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('ADMIN', 'CUSTOMER') NOT NULL
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS movies (
    movie_id VARCHAR(255) PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    description VARCHAR(2000) NOT NULL,
    duration INT NOT NULL,
    language VARCHAR(50) NOT NULL,
    genre VARCHAR(80) NOT NULL,
    release_date DATE NOT NULL,
    status ENUM('UPCOMING', 'NOW_SHOWING', 'ENDED') NOT NULL
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS theatre (
    theatre_id VARCHAR(255) PRIMARY KEY,
    theatre_name VARCHAR(150) NOT NULL,
    location VARCHAR(200) NOT NULL,
    capacity INT NOT NULL,
    status ENUM('ACTIVE', 'INACTIVE') NOT NULL
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS shows (
    show_id VARCHAR(255) PRIMARY KEY,
    movie_id VARCHAR(255) NOT NULL,
    theatre_id VARCHAR(255) NOT NULL,
    show_date DATE NOT NULL,
    show_time TIME NOT NULL,
    ticket_price DECIMAL(10,2) NOT NULL,
    status ENUM('SCHEDULED', 'COMPLETED', 'CANCELLED') NOT NULL,
    CONSTRAINT fk_shows_movie FOREIGN KEY (movie_id) REFERENCES movies(movie_id),
    CONSTRAINT fk_shows_theatre FOREIGN KEY (theatre_id) REFERENCES theatre(theatre_id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS booking (
    booking_id VARCHAR(255) PRIMARY KEY,
    user_id VARCHAR(255) NOT NULL,
    show_id VARCHAR(255) NOT NULL,
    seat_numbers VARCHAR(1000) NOT NULL,
    number_of_tickets INT NOT NULL,
    total_amount DECIMAL(10,2) NOT NULL,
    booking_date DATETIME(6) NOT NULL,
    status ENUM('PENDING', 'CONFIRMED', 'CANCELLED') NOT NULL,
    CONSTRAINT fk_booking_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_booking_show FOREIGN KEY (show_id) REFERENCES shows(show_id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS payments (
    payment_id VARCHAR(255) PRIMARY KEY,
    booking_id VARCHAR(255) NOT NULL UNIQUE,
    amount DECIMAL(10,2) NOT NULL,
    payment_date DATETIME(6) NOT NULL,
    payment_method VARCHAR(30) NOT NULL,
    status ENUM('PENDING', 'COMPLETED', 'FAILED', 'REFUNDED') NOT NULL,
    CONSTRAINT fk_payments_booking FOREIGN KEY (booking_id) REFERENCES booking(booking_id)
) ENGINE=InnoDB;

INSERT IGNORE INTO users (id, name, email, password, role) VALUES
    ('U-DEMO-ADMIN', 'Januly Fernando', 'admin@movies.lk', '$2a$10$6yA7G0dhFGX2YSwP8BEWmOcclGsmgji9m7CTXB1e75WGJKJVr8zSa', 'ADMIN'),
    ('U-DEMO-CUSTOMER', 'Nimal Perera', 'nimal@movies.lk', '$2a$10$n3sxGZyVzMU/YA2xYvh4ze/GNxDMlf3zXQDYlOA5khcZ31QjTIBca', 'CUSTOMER');

INSERT IGNORE INTO movies (movie_id, title, description, duration, language, genre, release_date, status) VALUES
    ('M-DEMO-1', 'Colombo Sihinaya', 'A young musician follows his dream in Colombo.', 120, 'Sinhala', 'Drama', DATE_SUB(CURDATE(), INTERVAL 7 DAY), 'NOW_SHOWING'),
    ('M-DEMO-2', 'Jaffna Iravu', 'A family story set in Jaffna.', 110, 'Tamil', 'Family', DATE_SUB(CURDATE(), INTERVAL 3 DAY), 'NOW_SHOWING'),
    ('M-DEMO-3', 'Kandy Yathra', 'An adventure through the hill country.', 130, 'Sinhala', 'Adventure', DATE_ADD(CURDATE(), INTERVAL 14 DAY), 'UPCOMING');

INSERT IGNORE INTO theatre (theatre_id, theatre_name, location, capacity, status) VALUES
    ('T-DEMO-1', 'Colombo City Cinema', 'Colombo 03', 40, 'ACTIVE'),
    ('T-DEMO-2', 'Kandy Lake Cinema', 'Kandy', 30, 'ACTIVE');

INSERT IGNORE INTO shows (show_id, movie_id, theatre_id, show_date, show_time, ticket_price, status) VALUES
    ('S-DEMO-1', 'M-DEMO-1', 'T-DEMO-1', DATE_ADD(CURDATE(), INTERVAL 1 DAY), '18:30:00', 1500.00, 'SCHEDULED'),
    ('S-DEMO-2', 'M-DEMO-2', 'T-DEMO-1', DATE_ADD(CURDATE(), INTERVAL 1 DAY), '21:00:00', 1200.00, 'SCHEDULED'),
    ('S-DEMO-3', 'M-DEMO-1', 'T-DEMO-2', DATE_ADD(CURDATE(), INTERVAL 2 DAY), '16:00:00', 1000.00, 'SCHEDULED');
