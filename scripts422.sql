create table human
(

    id              real,
    name            text primary key,
    age             integer,
    drivers_license boolean,
    car_id          integer references car (id)
);

create table car
(
    id    real primary key,
    brand varchar(255),
    model varchar(255),
    price numeric
);
