----------------------
-- Créer les tables
----------------------
CREATE TABLE IMAGES (
	id				INTEGER PRIMARY KEY AUTOINCREMENT,
	path				TEXT 	NOT NULL
	);	

CREATE TABLE LABELS (
	id			INTEGER 	PRIMARY KEY AUTOINCREMENT,
	imageId 		TEXT	NOT NULL,
	text 			TEXT	NOT NULL,
	dateAdded  		TEXT	NOT NULL,
	FOREIGN KEY(imageId) REFERENCES IMAGES(path)
	);
