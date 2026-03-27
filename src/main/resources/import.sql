insert into tb_categoria (nome, data_criacao) values('Livros', NOW());
insert into tb_categoria (nome, data_criacao) values('Notebook', NOW());
insert into tb_categoria (nome, data_criacao) values('Computadores', NOW());
insert into tb_categoria (nome, data_criacao) values('Brinquedos', NOW());

insert into tb_produto (nome, descricao,preco,imgURl,data_criacao) values('Brinquedos estrela 01','esse brinquedo é eletronico', 30.40, 'http://localhost:8080/estrela01.jpg', NOW());
insert into tb_produto (nome, descricao,preco,imgURl,data_criacao) values('Brinquedos estrela 02','esse brinquedo é eletronico', 40.40, 'http://localhost:8080/estrela02.jpg', NOW());
insert into tb_produto (nome, descricao,preco,imgURl,data_criacao) values('Brinquedos estrela 03','esse brinquedo é eletronico', 50.40, 'http://localhost:8080/estrela03.jpg', NOW());
insert into tb_produto (nome, descricao,preco,imgURl,data_criacao) values('Brinquedos estrela 04','esse brinquedo é eletronico', 60.40, 'http://localhost:8080/estrela04.jpg', NOW());
insert into tb_produto (nome, descricao,preco,imgURl,data_criacao) values('Brinquedos estrela 05','esse brinquedo é eletronico', 70.40, 'http://localhost:8080/estrela05.jpg', NOW());

insert into tb_perfil (nome) values ('ROLE_ADMINISTRADOR');
insert into tb_perfil (nome) values ('ROLE_VENDEDOR');
insert into tb_perfil (nome) values ('ROLE_CLIENTE');

insert into tb_usuario(nome, telefone, email, senha, data_criacao) values('Bruno', '1293456-7890', 'bruno.ferreira@ifmg.edu.br', '123456', NOW());
insert into tb_usuario(nome, telefone, email, senha, data_criacao) values('Maria', '1294456-7898', 'mariaedu11@gmail.com', '123456', NOW());