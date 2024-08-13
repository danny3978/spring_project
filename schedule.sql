# Schedule TABLE
create table if not exists scheduleUser
(
    id bigint auto_increment primary key,
    name varchar(255),
    password varchar(255),
    to_do varchar(1000),
    write_day datetime,
    update_day datetime
)