-- Webcam
INSERT INTO webcam (identifier, location) VALUES ("webcam1", "KyivKhreschatykStreet");
INSERT INTO webcam (identifier, location) VALUES ("webcam2", "KyivPeremohyAvenue");
INSERT INTO webcam (identifier, location) VALUES ("webcam3", "KyivIndependeceSquare");

-- Picture 1
INSERT INTO picture (created_time, fk_identifier) VALUES (NOW(), "webcam1");
INSERT INTO picture (created_time, fk_identifier) VALUES (NOW(), "webcam1");
INSERT INTO picture (created_time, fk_identifier) VALUES (NOW(), "webcam1");

-- Picture 2
INSERT INTO picture (created_time, fk_identifier) VALUES (NOW(), "webcam2");
INSERT INTO picture (created_time, fk_identifier) VALUES (NOW(), "webcam2");
INSERT INTO picture (created_time, fk_identifier) VALUES (NOW(), "webcam2");

-- Picture 3
INSERT INTO picture (created_time, fk_identifier) VALUES (NOW(), "webcam3");
INSERT INTO picture (created_time, fk_identifier) VALUES (NOW(), "webcam3");
INSERT INTO picture (created_time, fk_identifier) VALUES (NOW(), "webcam3");