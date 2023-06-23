create database hotel;
use hotel;

create table hotelaria(
  id integer not null auto_increment,
  nome varchar(50) not null, 
  endereco varchar(50) not null,
  primary key(id)
);

create table quarto(
  numQuarto integer not null auto_increment,
  andar varchar(50) not null, 
  tipo varchar(50) null,
  id_hotel integer not null,
  primary key(numQuarto),
  foreign key(id_hotel) references hotelaria(id)
);

select * from cliente;