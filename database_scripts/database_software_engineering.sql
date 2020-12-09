CREATE TABLE Applicant (
	id int auto_increment NOT NULL primary key,
    identity_card char(12),
    name varchar(150),
    phone char(10),
    birth date,
    gender int,
    address varchar(250),
    role int
);

CREATE TABLE Letter (
	id char(20) NOT NULL primary key,
	category varchar(150),
    problem varchar(100),
    id_applicant int,
    content varchar(200),
    apply_date date,
    status_letter int,
    deleted boolean,
    FOREIGN KEY (id_applicant) REFERENCES Applicant(id)
);

CREATE TABLE Problem (
    id int auto_increment NOT NULL primary key,
    name varchar(100)
);

CREATE TABLE GroupLetter  (
    id int auto_increment NOT NULL primary key,
    name varchar(100),
    status int,
    quantity int
);

CREATE TABLE GroupHasLetter(
    id_letter char(20) ,
    id_group int,
    primary key(id_letter, id_group),
    FOREIGN KEY (id_letter) REFERENCES Letter(id),
    FOREIGN KEY (id_group) REFERENCES GroupLetter(id)

);


CREATE TABLE History (
	id_applicant int,
    id_letter char(10),
    created_date date,
    processed_date date,
    status_log int,
    deleted boolean,
    FOREIGN KEY (id_applicant) REFERENCES Applicant(id),
    FOREIGN KEY (id_letter) REFERENCES Letter(id)
);



CREATE TABLE User(
	id_applicant int auto_increment NOT NULL primary key,
    id_card char(12),
    password varchar(20),
    deleted boolean,
    FOREIGN KEY (id_applicant) REFERENCES Applicant(id)
);

INSERT INTO Applicant VALUES(1,"1234","long user not admin","1234567879","2000-3-23",1,"ha noi",1);
INSERT INTO Applicant VALUES(2,"3154","truong user not admin","1234567879","2000-3-23",1,"nam dinh",1);

INSERT INTO Problem VALUES (1,"tainan");
INSERT INTO Problem VALUES (2,"vacham");

INSERT INTO Letter VALUES ("ND1564","kiennghi","tainan",1,"contetn1","2000-1-1",1,false);
INSERT INTO Letter VALUES ("HN15612","phananh","vacham",2,"contetn2","2000-1-1",1,false);