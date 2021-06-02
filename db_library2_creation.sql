DROP SCHEMA IF EXISTS library2;
CREATE SCHEMA library2 CHAR SET utf8mb4;
USE library2;

CREATE TABLE READER (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    readers_ticket_id VARCHAR(255) NOT NULL
);

INSERT INTO READER (first_name, last_name, readers_ticket_id) VALUES
('Dmytro', 'Chytach', 'AA546312'),
('Panas', 'Bibliofilovych', 'AA546310'); 

