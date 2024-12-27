drop table if exists likes;
drop table if exists message;
drop table if exists account;

create table account (
    accountId int primary key auto_increment,
    username varchar(255) not null unique,
    password varchar(255)
);

create table message (
    messageId int primary key auto_increment,
    postedBy int,
    messageText varchar(255),
    timePostedEpoch bigint,
    foreign key (postedBy) references account(accountId)
);

insert into account values (9999, 'testuser1', 'password');
insert into account values (9995, 'jupiter', 'ascending');
insert into account values (9998, 'testuser2', 'password');
insert into account values (9997, 'testuser3', 'password');
insert into account values (9996, 'testuser4', 'password');

insert into message values (9999, 9999,'test message 1',1669947792);
insert into message values (9997, 9997,'test message 2',1669947792);
insert into message values (9995, 9996,'User: 9996 post 1',1669947792);
insert into message values (9994, 9996,'User: 9996 post 2',1669947792);
insert into message values (9993, 9996,'User: 9996 post 3',1669947792);
insert into message values (9992, 9996,'User: 9996 post 4',1669947792);
insert into message values (9001, 9996, 'Off we go, into the Wild Blue Yonder......', 1669947793);
