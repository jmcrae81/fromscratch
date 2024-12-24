drop table if exists likes;
drop table if exists account;

create table account (
    accountId int primary key auto_increment,
    username varchar(255) not null unique,
    password varchar(255)
);

insert into account values (9999, 'testuser1', 'password');
insert into account values (9998, 'jupiter', 'ascending');
