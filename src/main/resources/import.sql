##### Fill Tables ###
#
# -- Webcam
# INSERT INTO webcam (identifier, location) VALUES ("webcam1", "KyivKhreschatykStreet");
# INSERT INTO webcam (identifier, location) VALUES ("webcam2", "KyivPeremohyAvenue");
# INSERT INTO webcam (identifier, location) VALUES ("webcam3", "KyivIndependeceSquare");

# -- Picture 1
# INSERT INTO picture (created_time, name, fk_identifier) VALUES (NOW(), "pic_name1", "webcam1");
# INSERT INTO picture (created_time, name, fk_identifier) VALUES (NOW(), "pic_name2", "webcam1");
# INSERT INTO picture (created_time, name, fk_identifier) VALUES (NOW(), "pic_name3", "webcam1");
#
# -- Picture 2
# INSERT INTO picture (created_time, name, fk_identifier) VALUES (NOW(), "pic_name4", "webcam2");
# INSERT INTO picture (created_time, name, fk_identifier) VALUES (NOW(), "pic_name5", "webcam2");
# INSERT INTO picture (created_time, name, fk_identifier) VALUES (NOW(), "pic_name6", "webcam2");
#
# -- Picture 3
# INSERT INTO picture (created_time, name, fk_identifier) VALUES (NOW(), "pic_name7", "webcam3");
# INSERT INTO picture (created_time, name, fk_identifier) VALUES (NOW(), "pic_name8", "webcam3");
# INSERT INTO picture (created_time, name, fk_identifier) VALUES (NOW(), "pic_name9", "webcam3");