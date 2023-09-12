insert into cliente (cpf, nome, telefone, email, data_nascimento) values ('11111111111', 'Josimar', '22555554444','josimar@gmail.com','2022-02-01');
insert into cliente (cpf, nome, telefone, email, data_nascimento) values ('22222222222', 'Maria', '22555554445','maria@gmail.com','2022-02-01');
insert into cliente (cpf, nome, telefone, email, data_nascimento) values ('33333333333', 'Jose', '22555554446','jose@gmail.com','2022-02-01');

insert into estado (nome,uf) values ('Goias','GO');
insert into estado (nome,uf) values ('Minas Gerais','MG');
insert into estado (nome,uf) values ('Distrito Federal','DF');
insert into estado (nome,uf) values ('Mato Grosso','MT');

insert into cidade (estado_id,nome) values (1,'Cristalina');
insert into cidade (estado_id,nome) values (2,'Paracatu');
insert into cidade (estado_id,nome) values (3,'Brasilia');
insert into cidade (estado_id,nome) values (4,'Roraima');

insert into cozinha (nome) values ('Indiana');
insert into cozinha (nome) values ('Japoneza');
insert into cozinha (nome) values ('Mexicana');
insert into cozinha (nome) values ('Mineira');

insert into produto (nome, descricao, preco, ativo) values ('sushi', 'peixe cru', 10.5, true);
insert into produto (nome, descricao, preco, ativo) values ('almoco', 'antes da janta', 15.15,true);
insert into produto (nome, descricao, preco, ativo) values ('jantar', 'depois do almoco', 25.15,true);
