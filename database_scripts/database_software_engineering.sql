CREATE TABLE Letter (
	id_letter int auto_increment NOT NULL primary key,
    title varchar(100),
    id_applicant int,
    content varchar(200),
    date_send date,
    category varchar(150),
    status_letter int,
    FOREIGN KEY (int_applicant) REFERENCES Applicant(id_applicant)
);

CREATE TABLE Applicant (
	id_applicant int auto_increment NOT NULL primary key,
    id_card long,
    name_apolicant varchar(150),
    birth date,
    address varchar(250)
    )