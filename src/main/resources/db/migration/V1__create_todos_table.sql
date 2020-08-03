
create table if not exists todos (
    id SERIAL primary key,
    name varchar(50) not null
);
