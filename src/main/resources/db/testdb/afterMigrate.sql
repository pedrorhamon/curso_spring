insert ignore into cozinha (id, nome) values (1, "Tailandesa");
insert ignore into cozinha (id, nome) values (2, "Indiana");
insert ignore into cozinha (id, nome) values (3, "Argentina");
insert ignore into cozinha (id, nome) values (4, "Brasileira");

insert into cozinha (nome) values ('Tailandesa');
insert into cozinha (nome) values ('Japonesa');
insert into cozinha (nome) values ('Brasil');

insert into restaurante (nome, taxa_frente, cozinha_id) values ('Restaurante_Tailandes', 11,1);
insert into restaurante (nome, taxa_frente, cozinha_id) values ('ChinaTaiwan', 48,00);
insert into restaurante (nome, taxa_frente, cozinha_id) values ('Feijoada da Chica', 50,00);