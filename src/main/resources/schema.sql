drop table if exists ingredient;
create table ingredient (
  id varchar(4) not null,
  name varchar(25) not null,
  type varchar(10) not null
);

drop table if exists taco;
create table taco (
  id bigint unsigned auto_increment,
  name varchar(50) not null,
  createdAt timestamp not null,
  primary key (id)
)engine =InnoDB default charset =utf8;

drop table if exists taco_ingredients;
create table taco_ingredients (
  taco bigint not null,
  ingredient varchar(4) not null
)engine =InnoDB default charset =utf8;


drop table if exists taco_order;
create table taco_order (
	id bigint unsigned auto_increment ,
	name varchar(50) not null,
	street varchar(50) not null,
	city varchar(50) not null,
	state varchar(2) not null,
	zip varchar(10) not null,
	ccNumber varchar(16) not null,
	ccExpiration varchar(5) not null,
	ccCVV varchar(3) not null,
    placedAt timestamp not null,
    primary key (id)
)engine =InnoDB default charset =utf8;

drop table if exists taco_order_tacos;
create table taco_order_tacos (
	tacoOrder bigint not null,
	taco bigint not null
)engine =InnoDB default charset =utf8;


# security用户表
drop table if exists users;
create table users (
    id bigint unsigned auto_increment,
    username varchar(50) not null,
    password text not null,
    enabled char(1) not null,
    primary key (id)
)engine =InnoDB default charset =utf8;

drop table if exists authorities;
create table authorities(
  id bigint unsigned auto_increment,
  username varchar(50) not null,
  authority varchar(20) not null,
  primary key (id)
)engine =InnoDB default charset =utf8;

drop table if exists groups;
create table groups(
    id bigint unsigned auto_increment,
    group_name varchar(50),
    primary key (id)
)engine =InnoDB default charset =utf8;

drop table if exists group_authorities;
create table group_authorities(
    id bigint unsigned auto_increment,
    group_id bigint not null,
    authority varchar(20) not null,
    primary key (id)
)engine =InnoDB default charset =utf8;

drop table if exists group_members;
create table group_members(
    id bigint unsigned auto_increment,
    group_id bigint not null,
    username varchar(50) not null,
    primary key (id)
)engine =InnoDB default charset =utf8;