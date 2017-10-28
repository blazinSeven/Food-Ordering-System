CREATE TABLE users (
        id SERIAL,
        username VARCHAR (20) NOT NULL,
        passwords VARCHAR(16) NOT NULL,
        user_status VARCHAR(1) NOT NULL CHECK (user_status IN ('c','r','a')),
        PRIMARY KEY (id));

CREATE TABLE customers(
    id SERIAL,
    user_id SERIAL NOT NULL REFERENCES users,
    customer_first_name VARCHAR(20) NOT NULL,
    customer_last_name VARCHAR(20) NOT NULL,
    phone_num VARCHAR(11) NOT NULL,
    e_mail_address VARCHAR(50) NOT NULL,
    pref_food VARCHAR (20),
    PRIMARY KEY (id));

CREATE TABLE restuarants (
    id SERIAL,
    user_id SERIAL NOT NULL REFERENCES users,
    restuarant_name VARCHAR (30) NOT NULL,
    license_id VARCHAR (40) NOT NULL,
    open_time TIME NOT NULL,
    close_time TIME NOT NULL,
    e_mail_address VARCHAR(50),
    PRIMARY KEY (id));

CREATE TABLE locations (
    location_id SERIAL,
    customer_id SERIAL NOT NULL REFERENCES customers,
    restuarant_id SERIAL NOT NULL REFERENCES restuarants,
    house_num NUMERIC NOT NULL,
    street VARCHAR (40) NOT NULL,
    city VARCHAR (30) NOT NULL,
    province VARCHAR (15) NOT NULL,
    post_code VARCHAR (7) NOT NULL,
    PRIMARY KEY (location_id)
);

CREATE TABLE menus (
    menu_id SERIAL,
    restuarant_id SERIAL NOT NULL REFERENCES restuarants,
    menu_name VARCHAR(30) NOT NULL,
    PRIMARY KEY (menu_id)
);

CREATE TABLE dishes (
    id SERIAL,
    menus_id SERIAL NOT NULL REFERENCES menus,
    dish_name VARCHAR(30) NOT NULL,
    dish_prices NUMERIC NOT NULL,
    dish_preparation_time FLOAT NOT NULL,
    PRIMARY KEY (id));

CREATE TABLE reviews (
    id SERIAL,
    customer_id SERIAL NOT NULL REFERENCES customers,
    restuarant_id SERIAL NOT NULL REFERENCES restuarants,
    stars FLOAT NOT NULL,
    comments VARCHAR(300),
    PRIMARY KEY (id));

CREATE TABLE orders (
    id SERIAL,
    order_date DATE NOT NULL,
    order_time TIME,
    customer_id SERIAL NOT NULL REFERENCES customers,
    restuarant_id SERIAL NOT NULL REFERENCES restuarants,
    subtotal NUMERIC NOT NULL,
    pst_tax NUMERIC NOT NULL,
    gst_tax NUMERIC NOT NULL,
    total_discount NUMERIC,
    PRIMARY KEY (id));

CREATE TABLE order_lines(
    id SERIAL,
    order_id SERIAL NOT NULL REFERENCES orders,
    dishes_id SERIAL NOT NULL REFERENCES dishes,
    quanity INT NOT NULL,
    price_per_one NUMERIC NOT NULL,
    price_total NUMERIC NOT NULL,
    discount_total NUMERIC,
    PRIMARY KEY (id));


CREATE TABLE payments (
    id SERIAL,
    orders_id SERIAL NOT NULL REFERENCES orders,
    payment_method VARCHAR(4) CHECK  (payment_method IN ('card','cash')),
    PRIMARY KEY (id));

CREATE TABLE card_payment(
    id SERIAL,
    payment_id SERIAL NOT NULL REFERENCES payments,
    card_num NUMERIC(16) NOT NULL,
    card_name VARCHAR (30) NOT NULL,
    card_code NUMERIC (3) NOT NULL,
    payment_price NUMERIC NOT NULL,
    PRIMARY KEY (id));

CREATE TABLE cash_payment(
    id SERIAL,
    payment_id SERIAL NOT NULL REFERENCES payments,
    PRIMARY KEY (id));
