-- Webcam
INSERT INTO webcam (identifier, location) VALUES ("webcam#1", "kyiv/khreschatyk_street");
INSERT INTO webcam (identifier, location) VALUES ("webcam#2", "kyiv/peremohy_avenue");
INSERT INTO webcam (identifier, location) VALUES ("webcam#3", "kyiv/independece_square");

-- Picture 1
INSERT INTO picture (created_time, fk_identifier) VALUES (NOW(), "webcam#1");
INSERT INTO picture (created_time, fk_identifier) VALUES (NOW(), "webcam#1");
INSERT INTO picture (created_time, fk_identifier) VALUES (NOW(), "webcam#1");

-- Picture 2
INSERT INTO picture (created_time, fk_identifier) VALUES (NOW(), "webcam#2");
INSERT INTO picture (created_time, fk_identifier) VALUES (NOW(), "webcam#2");
INSERT INTO picture (created_time, fk_identifier) VALUES (NOW(), "webcam#2");

-- Picture 3
INSERT INTO picture (created_time, fk_identifier) VALUES (NOW(), "webcam#3");
INSERT INTO picture (created_time, fk_identifier) VALUES (NOW(), "webcam#3");
INSERT INTO picture (created_time, fk_identifier) VALUES (NOW(), "webcam#3");