CREATE DEFINER=`root`@`localhost` PROCEDURE `activarEditorial`(pId varchar(255))
SALIR:BEGIN
	-- Manejo de error en la transacción
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN 
		SHOW ERRORS;
		SELECT 'Error en la transacción. Contáctese con el administrador.' Mensaje;
		ROLLBACK;
	END;
   -- Controla que el id  sea obligatorio. 
	IF pId = '' OR pId IS NULL THEN
		SELECT 'Debe proveer un identificador del editorial.' AS Mensaje;
		LEAVE SALIR;
    END IF;
      -- Controla que el editorial sea obligatorio 
	IF NOT EXISTS(SELECT * FROM editorial WHERE id = pId) THEN
		SELECT 'Debe proveer un editorial existente.' AS Mensaje;
		LEAVE SALIR;
    END IF;
    
    	IF (SELECT status FROM editorial WHERE id = pId) = 1 THEN
		SELECT 'el editorial  ya está activo.' AS Mensaje;
        LEAVE SALIR;
	END IF;
    
    
 	START TRANSACTION;
		Update editorial SET status = true WHERE  editorial.id =pId;
		SELECT 'OK' AS Mensaje;
		COMMIT;
END