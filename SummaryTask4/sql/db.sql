CREATE TABLE roles (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(15) NOT NULL,
	PRIMARY KEY (id)
);

INSERT INTO roles VALUES(DEFAULT, 'admin');
INSERT INTO roles VALUES(DEFAULT, 'teacher');
INSERT INTO roles VALUES(DEFAULT, 'student');


CREATE TABLE statuses (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(15) NOT NULL,
	PRIMARY KEY (id)
);

INSERT INTO statuses VALUES(DEFAULT, 'active');
INSERT INTO statuses VALUES(DEFAULT, 'blocked');


CREATE TABLE users (
	id INT NOT NULL AUTO_INCREMENT,
	email VARCHAR(100) NOT NULL UNIQUE,
	password VARCHAR(100) NOT NULL,
	first_name VARCHAR(200) NOT NULL,
	last_name VARCHAR(200) NOT NULL,
	role_id INT NOT NULL DEFAULT 3,
	status_id INT NOT NULL DEFAULT 1,
	PRIMARY KEY (id),
	FOREIGN KEY (role_id) REFERENCES roles (id),
	FOREIGN KEY (status_id) REFERENCES statuses (id)
);

INSERT INTO users VALUES(DEFAULT, 'admin@gmail.com', '777777', 'Ihor', 'Starodubets', 1, DEFAULT);
INSERT INTO users VALUES(DEFAULT, 'gosling@gmail.com', '777777', 'James', 'Gosling', 2, DEFAULT);
INSERT INTO users VALUES(DEFAULT, 'hetfield@gmail.com', '777777', 'James', 'Hetfield', 3, DEFAULT);
INSERT INTO users VALUES(DEFAULT, 'ulrich@gmail.com', '777777', 'Lars', 'Ulrich', 3, DEFAULT);
INSERT INTO users VALUES(DEFAULT, 'hammett@gmail.com', '777777', 'Kirk', 'Hammett', 3, DEFAULT);
INSERT INTO users VALUES(DEFAULT, 'trujillo@gmail.com', '777777', 'Robert', 'Trujillo', 3, DEFAULT);


CREATE TABLE subjects (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(200) NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE courses (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(200) NOT NULL,
	course_start_date DATE NOT NULL,
	course_end_date DATE NOT NULL,
	duration INT NOT NULL,
	number_students INT DEFAULT 0,
	subject_id INT NOT NULL,
	teacher_id INT NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (subject_id) REFERENCES subjects (id),
	FOREIGN KEY (teacher_id) REFERENCES users (id)
);


CREATE TABLE journals (
	id INT NOT NULL AUTO_INCREMENT,
	course_id INT NOT NULL,
	teacher_id INT NOT NULL,
	student_id INT NOT NULL,
	rating INT NOT NULL DEFAULT 0,
	PRIMARY KEY (id),
	FOREIGN KEY (course_id) REFERENCES courses (id),
	FOREIGN KEY (teacher_id) REFERENCES users (id),
	FOREIGN KEY (student_id) REFERENCES users (id)
);

