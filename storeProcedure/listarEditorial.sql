CREATE DEFINER=`root`@`localhost` PROCEDURE `listarEditorial`()
BEGIN
SELECT id, name, status FROM editorial;
END