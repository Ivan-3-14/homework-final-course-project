create database people;

use people;

create table person
(
    id             int auto_increment
        primary key,
    age            int                                 not null,
    salary         decimal                             null,
    passport       varchar(10)                         null,
    address        varchar(200)                        null,
    dateOfBirthday date                                null,
    dateTimeCreate timestamp default CURRENT_TIMESTAMP null,
    timeToLunch    time                                null,
    ` letter`      text                                null
);

insert into person (age, salary, passport, address, dateOfBirthday,
                    timeToLunch, ` letter`)
values (33, 850.5, 'MP3911255', 'Belarus, Minsk, Tanka str, 2',
        '1991-01-11', '13:00:00', 'hello'),
       (2, 0.0, 'MC3915654', 'Minsk,Korzuky, 2', '2022-01-01',
        '11:00:00', 'small girl'),
       (20, 700.00, 'MC222121', 'Brest, Pogranichnikov, 116', '2003-12-24',
        '12:00:00', 'yong people'),
       (14, 0.0, 'MC2345678', 'Mir, Slonovaya, 5', '2010-01-7', '14:00:00',
        'understand you'),
       (22, 1450.6, 'MR1234569', 'Minsk, Komsomolskaya, 112/6', '2001-9-30',
        '13:00:00', 'richy');

select * from person
where age > 21 order by dateTimeCreate