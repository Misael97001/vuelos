CREATE TABLE vuelos(
	id SERIAL PRIMARY KEY,
	codigo VARCHAR(10) NOT NULL UNIQUE,
	precio_boleto NUMERIC(10,2) NOT NULL CHECK(precio_boleto>=0),
	asientos_disponibles INTEGER NOT NULL CHECK(asientos_disponibles>=0)
);

INSERT INTO vuelos
(codigo, precio_boleto, asientos_disponibles)
VALUES
('AA-101',320.50,45),
('AA-102',280.00,30),
('AA-103',150.00,10),
('AA-104',180.00,3),
('AA-105',220.00,50),
('AA-106',410.00,4),
('AA-107',330.00,12),
('AA-108',200.00,7),
('AA-109',250.00,0),
('AA-110',290.00,20),
('AA-111',360.00,40),
('AA-112',310.00,1),
('AA-113',170.00,25),
('AA-114',210.00,18),
('AA-115',240.00,60),
('AA-116',380.00,8),
('AA-117',275.00,0),
('AA-118',195.00,15),
('AA-119',230.00,6),
('AA-120',260.00,2);

SELECT *
FROM vuelos
WHERE asientos_disponibles < 5;


UPDATE vuelos
SET precio_boleto = precio_boleto * 1.15
WHERE id = 1;


DELETE FROM vuelos
WHERE asientos_disponibles = 0;