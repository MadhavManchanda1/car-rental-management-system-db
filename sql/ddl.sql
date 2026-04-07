DROP TABLE IF EXISTS Payment;
DROP TABLE IF EXISTS Rental;
DROP TABLE IF EXISTS Reservation;
DROP TABLE IF EXISTS Car;
DROP TABLE IF EXISTS Customer;

CREATE TABLE Customer (
    customer_id INTEGER PRIMARY KEY,
    name TEXT,
    phone TEXT,
    email TEXT,
    driver_license_no TEXT
);

CREATE TABLE Car (
    car_id INTEGER PRIMARY KEY,
    brand TEXT,
    model TEXT,
    year INTEGER,
    daily_rate REAL,
    availability_status TEXT
);

CREATE TABLE Reservation (
    reservation_id INTEGER PRIMARY KEY,
    customer_id INTEGER,
    car_id INTEGER,
    start_date TEXT,
    end_date TEXT,
    FOREIGN KEY (customer_id) REFERENCES Customer(customer_id),
    FOREIGN KEY (car_id) REFERENCES Car(car_id)
);

CREATE TABLE Rental (
    rental_id INTEGER PRIMARY KEY,
    reservation_id INTEGER,
    pickup_date TEXT,
    return_date TEXT,
    total_cost REAL,
    FOREIGN KEY (reservation_id) REFERENCES Reservation(reservation_id)
);

CREATE TABLE Payment (
    payment_id INTEGER PRIMARY KEY,
    rental_id INTEGER,
    payment_date TEXT,
    amount REAL,
    payment_method TEXT,
    FOREIGN KEY (rental_id) REFERENCES Rental(rental_id)
);