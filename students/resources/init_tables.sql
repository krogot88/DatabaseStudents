CREATE TABLE groups ( 
   id BIGINT auto_increment,
   numer INT NOT NULL UNIQUE,
   faculty VARCHAR(25) NOT NULL, 
);

CREATE TABLE students (
	id BIGINT auto_increment,
	firstName VARCHAR(25) NOT NULL,
	lastName VARCHAR(25) NOT NULL,
	fatherName VARCHAR(25) NOT NULL,
	dateOfBirth DATE NOT NULL,	
	groupNumber BIGINT,
	FOREIGN KEY (groupNumber)  REFERENCES groups(id) ON DELETE SET NULL,
);


INSERT INTO groups(numer,faculty) VALUES (10,'ASOIY'); 
INSERT INTO groups(numer,faculty) VALUES (20,'DVS'); 
INSERT INTO groups(numer,faculty) VALUES (30,'RADIO'); 
INSERT INTO groups(numer,faculty) VALUES (40,'ASTRONOMY'); 

INSERT INTO students(firstName,lastName,fatherName,dateOfBirth,groupNumber) VALUES ('Александр','Молостов','Андреевич','1986-02-27',1);
INSERT INTO students(firstName,lastName,fatherName,dateOfBirth,groupNumber) VALUES ('Дмитрий','Кузнецов','Сергеевич','1985-03-20',1);
INSERT INTO students(firstName,lastName,fatherName,dateOfBirth,groupNumber) VALUES ('Петр','Сидоров','Иваночив','1986-06-10',2);
INSERT INTO students(firstName,lastName,fatherName,dateOfBirth,groupNumber) VALUES ('Агафий','Петрушев','Валентинович','1987-11-02',2);
INSERT INTO students(firstName,lastName,fatherName,dateOfBirth,groupNumber) VALUES ('Василий','Корнев','Евгенеевич','1987-11-02',3);
INSERT INTO students(firstName,lastName,fatherName,dateOfBirth,groupNumber) VALUES ('Евгений','Бирюков','Александрович','1987-11-02',1);
INSERT INTO students(firstName,lastName,fatherName,dateOfBirth,groupNumber) VALUES ('Семен','Князев','Валерьевич','1987-11-02',3);
