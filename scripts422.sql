CREATE TABLE cars
(
    id    SERIAL PRIMARY KEY,
    brand TEXT  NOT NULL,
    model TEXT  NOT NULL,
    price MONEY NOT NULL CHECK ( price > 0 )
);
CREATE TABLE humans
(
    id         SERIAL PRIMARY KEY,
    name       TEXT NOT NULL,
    age        INTEGER CHECK (age > 0),
    carLicense BOOLEAN,
    car_id     INTEGER REFERENCES cars (id)
);

