### Fill Tables ###

-- Webcam
INSERT INTO webcam (identifier, location) VALUES ("webcam1", "Kyiv");
INSERT INTO webcam (identifier, location) VALUES ("webcam2", "Lviv");
INSERT INTO webcam (identifier, location) VALUES ("webcam3", "Dnipro");

# -- Picture 1
# INSERT INTO picture (created_time, name, fk_identifier) VALUES ("2017-01-04 12:00:00", "background1.jpg", "webcam1");
# INSERT INTO picture (created_time, name, fk_identifier) VALUES ("2017-02-05 13:30:00", "background2.jpg", "webcam1");
#
# -- Picture 2
# INSERT INTO picture (created_time, name, fk_identifier) VALUES ("2017-01-04 15:30:00", "background3.jpg", "webcam2");
# INSERT INTO picture (created_time, name, fk_identifier) VALUES ("2017-02-05 16:30:00", "background4.jpg", "webcam2");
#
# -- Picture 3
# INSERT INTO picture (created_time, name, fk_identifier) VALUES ("2017-01-04 18:30:00", "background5.jpg", "webcam3");
# INSERT INTO picture (created_time, name, fk_identifier) VALUES ("2017-02-05 19:30:00", "background6.jpg", "webcam3");
