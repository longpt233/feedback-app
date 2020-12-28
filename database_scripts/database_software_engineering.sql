CREATE TABLE Applicant (
	id int auto_increment NOT NULL primary key,
    identity_card char(12) UNIQUE,
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

INSERT INTO Applicant VALUES(1,"11111","Phan Thanh Long","1234567879","2000-3-23",1,"Hải Dương",1);
INSERT INTO Applicant VALUES(2,"22222","Trần Thanh Duyên","1234567879","2000-9-13",1,"Nam Định",1);
INSERT INTO Applicant VALUES(3,"33333","Bùi Thị Hà","1234567879","2003-3-17",0,"Hải Dương",1);
INSERT INTO Applicant VALUES(4,"44444","Nguyễn Trà My","1234567879","1999-1-24",0,"Thanh Hóa",1);
INSERT INTO Applicant VALUES(5,"55555","Nguyễn Minh Quân","1234567879","1994-5-23",1,"Khánh Hòa",1);
INSERT INTO Applicant VALUES(6,"66666","Vũ Mĩ Linh","1234567879","2001-3-27",0,"Hải Phòng",1);


INSERT INTO Problem VALUES (1,"Tai Nạn");
INSERT INTO Problem VALUES (2,"Va Chạm");
INSERT INTO Problem VALUES (3,"Ô Nhiễm Môi Trường");

INSERT INTO Letter VALUES ("ND1564","Phản Ánh","Ô Nhiễm Môi Trường","11111","Sông có người đổ rác trộm","Tổ Dân Phố","2020-04-10",1,false);
INSERT INTO Letter VALUES ("HN1111","Phản Ánh","Va Chạm","11111","Ngày 27/12/2020 trên đường Trần Đại Nghĩa có người gây tai nạn rồi bỏ chạy ","Công An Hà Nội","2020-12-07",1,false);
INSERT INTO Letter VALUES ("HN1112","Phản Ánh","Ô Nhiễm Môi Trường","55555","Cổng nhà tôi bị trở thành nơi đổ rác trộm","Tổ Dân Phố","2020-05-17",1,false);
INSERT INTO Letter VALUES ("KM2134","Kiến Nghị","Tai Nạn","66666","Yêu cầu giải quyết vụ tai nạn","Tòa Án tỉnh Hà Nội","2020-09-01",1,false);
INSERT INTO Letter VALUES ("HN1233","Phản Ánh","Tai Nạn","44444","Đoạn đường thường xuyên xảy ra tai nạn","Công An Hà Nội","2000-1-1",1,false);
INSERT INTO Letter VALUES ("HD1233","Phản Ánh","Va Chạm","33333","Ngày 27/12/2020 trên đường Trần Đại Nghĩa có người gây tai nạn rồi bỏ chạy ","Công An Hà Nội","2020-12-08",1,false);
