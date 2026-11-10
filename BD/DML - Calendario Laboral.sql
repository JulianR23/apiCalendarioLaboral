--Registros tabla TIPO
INSERT INTO Tipo(Id, Tipo) VALUES(1, 'Día laboral');
INSERT INTO Tipo(Id, Tipo) VALUES(2, 'Fin de Semana ');
INSERT INTO Tipo(Id, Tipo) VALUES(3, 'Día festivo ');

--Sincronizar la secuencia del Id para que los nuevos registros no choquen con los ya insertados
SELECT setval(pg_get_serial_sequence('tipo', 'id'), (SELECT MAX(Id) FROM Tipo));


