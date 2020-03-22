DROP TABLE IF EXISTS ticket;
 
CREATE TABLE ticket (
  id INT PRIMARY KEY,
  bought BOOLEAN DEFAULT FALSE,
  picked BOOLEAN DEFAULT FALSE,
  user VARCHAR(250) DEFAULT NULL
);
 
DROP TABLE IF EXISTS config;
 
CREATE TABLE config (
  id VARCHAR PRIMARY KEY,
  value VARCHAR NOT NULL
);


INSERT INTO Ticket (id) VALUES (1);
INSERT INTO Ticket (id) VALUES (2);
INSERT INTO Ticket (id) VALUES (3);
INSERT INTO Ticket (id) VALUES (4);
INSERT INTO Ticket (id) VALUES (5);

INSERT INTO Ticket (id, bought) VALUES (6, true);
INSERT INTO Ticket (id, bought) VALUES (7, true);
INSERT INTO Ticket (id, bought) VALUES (8, true);
