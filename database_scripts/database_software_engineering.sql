CREATE TABLE Letter (
	id int auto_increment NOT NULL primary key,
    title varchar(100),
    id_applicant int,
    content varchar(200),
    category varchar(150),
    apply_date date,
    status_letter int,
    deleted boolean,
    FOREIGN KEY (id_applicant) REFERENCES Applicant(id)
);

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

CREATE TABLE History (
	id_applicant int,
    id_letter int,
    created_date date,
    processed_date date,
    status_log int,
    deleted boolean,
    FOREIGN KEY (id_applicant) REFERENCES Applicant(id),
    FOREIGN KEY (id_letter) REFERENCES Letter(id)
);

CREATE TABLE Organization(
	id_organization int auto_increment NOT NULL primary key,
    name varchar(250),
    funtion varchar(250),
    level int,
    deleted boolean
);

CREATE TABLE User(
	id_applicant int auto_increment NOT NULL primary key,
    id_card char(12),
    password varchar(20),
    deleted boolean,
    FOREIGN KEY (id_applicant) REFERENCES Applicant(id),
);

INSERT INTO Applicant VALUES(1,"1234","long user not admin","1234567879","2000-3-23",1,"ha noi",1);
INSERT INTO Letter VALUES (1,"kiennghi1",1,"contetn1","the loai1","2000-1-1",1,false);
