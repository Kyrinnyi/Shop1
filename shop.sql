create database shop; 

use shop;                  
                                 

create table admins (
  login    varchar(20) not null, 
  pass    varchar(20),
  primary key (login)  
);


create table clients (
  id    mediumint(9) not null auto_increment, 
  name  varchar(20) not null, 
  surename  varchar(20) not null,
  phone  varchar(20) not null,
  mail  varchar(30) not null,
  addres  varchar(30) not null,
  primary key (id)  
);

create table black (
  id    mediumint(9) not null auto_increment, 
  client_id    mediumint(9) not null, 
  primary key (id),  
  FOREIGN KEY (client_id) REFERENCES clients (id)
);

create table goods (
  id    mediumint(9) not null auto_increment,  
  name  varchar(30), 
  description  longtext,
  price  float,
  picture  varchar(50),
  available  mediumint(9),
  gr  varchar(20),
  primary key (id)  
);

create table goods_ru like goods;

create table orders (
  idOrders    int(11) not null auto_increment, 
  idClient    mediumint(9) not null, 
  paid tinyint(4),
  confirmed tinyint(4),
  closed tinyint(4),
  primary key (idOrders), 
  FOREIGN KEY (idClient)  REFERENCES clients (id)
);

create table og (
  idog    int(11) not null auto_increment, 
  idgoods    mediumint(9) not null, 
  idorder    int(11) not null, 
  amount    int(11),
  primary key (idog), 
  FOREIGN KEY (idgoods) REFERENCES goods (id),
  FOREIGN KEY (idorder) REFERENCES orders (idOrders) 
);


insert into admins 
values ('root','root');

commit;