DROP DATABASE IF EXISTS diecast_finder_wanted;
DROP USER IF EXISTS `diecast_finder_wanted`@`%`;
CREATE DATABASE IF NOT EXISTS diecast_finder_wanted CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS `diecast_finder_wanted`@`%` IDENTIFIED BY 'password_wan';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW,
    CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `diecast_finder_wanted`.* TO `diecast_finder_wanted`@`%`;
FLUSH PRIVILEGES;