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
    id_card_applicant char(12),
    content varchar(200),
    organization varchar(50),
    apply_date date,
    status_letter int,
    deleted boolean,
    FOREIGN KEY (id_card_applicant) REFERENCES Applicant(identity_card)
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

INSERT INTO Applicant VALUES(1,"11111","phan thanh long ","1234567879","2000-3-23",1,"hai duong",1);
INSERT INTO Applicant VALUES(2,"22222","Tran Thanh Duyen","1234567879","2000-9-13",1,"nam dinh",1);
INSERT INTO Applicant VALUES(3,"33333","Bui Thi Ha","1234567879","2003-3-17",0,"hai duong",1);
INSERT INTO Applicant VALUES(4,"44444","Nguyen Tra My","1234567879","1999-1-24",0,"thanh hoa",1);
INSERT INTO Applicant VALUES(5,"55555","Nguyen Minh Quan","1234567879","1994-5-23",1,"khanh hoa",1);
INSERT INTO Applicant VALUES(6,"66666","Vu My Linh","1234567879","2001-3-27",0,"hai phong",1);


INSERT INTO Problem VALUES (1,"tainan");
INSERT INTO Problem VALUES (2,"vacham");
INSERT INTO Problem VALUES (3,"onhiemmoitruong");

INSERT INTO Letter VALUES ("ND1564","kiennghi","tainan","11111","contetn1","cong ti A","2000-1-1",1,false);
INSERT INTO Letter VALUES ("HN1111","phananh","vacham","11111","contetn2","bo truong bo quoc phong","2000-1-1",1,false);
INSERT INTO Letter VALUES ("HN1112","phananh","onhiemmoitruong","55555","contetn3","bo truong bo kinh te","2000-1-1",1,false);
INSERT INTO Letter VALUES ("KM2134","kiennghi","tainan","66666","contetn4","bo truong bo ngoaigiao ","2000-1-1",1,false);
INSERT INTO Letter VALUES ("HN1233","phananh","vacham","44444","contetn5","bo truong ","2000-1-1",1,false);
INSERT INTO Letter VALUES ("HD1233","phananh","vacham","33333","contetn6","truong dai hoc bach khoa","2000-1-1",1,false);
