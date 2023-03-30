use ssafytest;
create table user(
id varchar(10) NOT NULL,
name varchar(10) NOT NULL,
pw varchar(10) NOT NULL,
email varchar(20) NOT NULL,
loc varchar(20) NOT NULL,
PRIMARY KEY (`id`),
UNIQUE KEY `id` (`id`)
);