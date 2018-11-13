CREATE TABLE product
(
   	id  SERIAL PRIMARY KEY      NOT NULL,
    creation_date timestamp  not null,
    name text,
    price real
)

ALTER TABLE product
    OWNER to postgres;