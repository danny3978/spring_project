# Schedule TABLE
create table if not exists scheduleUser
(
    id bigint,
    name varchar(100),
    password varchar(100),
    to_do varchar(1000),
    write_day date,
    update_day date,
    primary key (id)
)