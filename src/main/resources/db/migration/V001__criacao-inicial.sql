create table cidade (estado_id bigint not null, id bigint not null auto_increment, nome varchar(40), primary key (id)) engine=InnoDB;
create table cliente (data_nascimento date, id bigint not null auto_increment, cpf varchar(11), telefone varchar(13), email varchar(40) not null, nome varchar(50) not null, primary key (id)) engine=InnoDB;
create table cozinha (id bigint not null auto_increment, nome varchar(40), primary key (id)) engine=InnoDB;
create table estado (uf varchar(2) not null, id bigint not null auto_increment, nome varchar(40), primary key (id)) engine=InnoDB;
create table produto (ativo bit, preco decimal(38,2), id bigint not null auto_increment, descricao varchar(255), nome varchar(255), primary key (id)) engine=InnoDB;
alter table if exists cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id);