-- DROP SCHEMA bd2021 CASCADE;

CREATE SCHEMA bd2021;

CREATE TABLE bd2021.loja(
	id SERIAL,
	nome VARCHAR(100),
	loja_link VARCHAR(200),
	loja_img VARCHAR(200),
	CONSTRAINT pk_loja PRIMARY KEY(id)
);

CREATE TABLE bd2021.jogo(
	titulo VARCHAR(100) NOT NULL,
	desenvolvedora VARCHAR(100) NOT NULL,
	categoria VARCHAR(20),
	descricao text,
	publicadora VARCHAR(50),
	ano_publicacao DATE,
	CONSTRAINT pk_jogo PRIMARY KEY(titulo, desenvolvedora)
);

CREATE TABLE bd2021.requisito(
	jogo_titulo VARCHAR(100) NOT NULL,
	jogo_desenvolvedora VARCHAR(100) NOT NULL,
	cpu VARCHAR(100),
	gpu VARCHAR(100),
	memoria_ram INT,
	so VARCHAR(50),
	armazenamento INT,
	CONSTRAINT fk_jogo_requisito FOREIGN KEY(jogo_titulo, jogo_desenvolvedora)
	REFERENCES bd2021.jogo(titulo, desenvolvedora)
);


CREATE TABLE bd2021.preco_data(
	data_registro DATE,
	preco NUMERIC(6,2),
	porcentagem_promo INT,
	data_jogo_titulo VARCHAR(100) NOT NULL,
	data_jogo_desenvolvedora VARCHAR(100) NOT NULL,
	data_loja_id INT NOT NULL,
	CONSTRAINT fk_jogo_preco_data FOREIGN KEY(data_jogo_titulo, data_jogo_desenvolvedora)
	REFERENCES bd2021.jogo(titulo, desenvolvedora),
	CONSTRAINT fk_loja_preco_data FOREIGN KEY(data_loja_id)
	REFERENCES bd2021.loja(id),
	CONSTRAINT pk_preco_data PRIMARY KEY(data_registro, data_jogo_titulo, data_jogo_desenvolvedora,data_loja_id)
);

CREATE TABLE bd2021.historico_por_loja(
	data_hpl DATE,
	menor_preco NUMERIC(6,2),
	media_preco NUMERIC(6,2),
	maior_promo INT,
	jogo_titulo VARCHAR(100) NOT NULL,
	jogo_desenvolvedora VARCHAR(100) NOT NULL,
	loja_id INT NOT NULL,
	CONSTRAINT fk_preco_data_hpl FOREIGN KEY(data_hpl,jogo_titulo, jogo_desenvolvedora, loja_id)
	REFERENCES bd2021.preco_data(data_registro, data_jogo_titulo, data_jogo_desenvolvedora,data_loja_id),
	CONSTRAINT pk_hpl PRIMARY KEY(jogo_titulo, jogo_desenvolvedora, loja_id)
);


-- INSERT INTO bd2021.loja VALUES (DEFAULT,'steam','https://store.steampowered.com/?l=portuguese','assets/images/logos/steam_logo.png');
-- INSERT INTO bd2021.loja VALUES (DEFAULT,'nuuvem','https://www.nuuvem.com','assets/images/logos/nuuvem_logo.png');
-- INSERT INTO bd2021.loja VALUES (DEFAULT,'epicgames','https://www.epicgames.com/store/pt-BR/','assets/images/logos/epicgames_logo.png');

-- INSERT INTO bd2021.jogo VALUES ('Valheim','Iron Gate AB','aventura','joguinho legal','tantofaz','2018-10-10');
-- INSERT INTO bd2021.jogo VALUES ('MUK','Iron Gate AB','aventura','joguinho legal','tantofaz','2018-10-10');
-- INSERT INTO bd2021.jogo VALUES ('Call of duty','Iron Gate AB','aventura','joguinho legal','tantofaz','2018-10-10');

-- INSERT INTO bd2021.jogo VALUES ('gta','Iron Gate AB','aventura','joguinho legal','tantofaz','2018-10-10');
-- INSERT INTO bd2021.jogo VALUES ('Tibia','AC','aventura','joguinho legal','tantofaz','2018-10-10');
-- INSERT INTO bd2021.jogo VALUES ('Pokemon','Iron Gate AB','aventura','joguinho legal','nintendo','2018-10-10');

-- INSERT INTO bd2021.jogo VALUES ('tft','riot','aventura','joguinho legal','tantofaz','2018-10-10');
-- INSERT INTO bd2021.jogo VALUES ('lol','riot','aventura','joguinho legal','tantofaz','2018-10-10');
-- INSERT INTO bd2021.jogo VALUES ('valorant','riot','aventura','joguinho legal','nintendo','2018-10-10');

-- INSERT INTO bd2021.requisito VALUES ('Valheim', 'Iron Gate AB', 'i7-1100', 'Ryzen 5 5600G', '4', 'ruimdows', '2');
-- INSERT INTO bd2021.requisito VALUES ('MUK', 'Iron Gate AB', 'i5-7700', 'Ryzen 5 5600G', '4', 'ruimdows', '2');
-- INSERT INTO bd2021.requisito VALUES ('Pokemon', 'Iron Gate AB', 'i5-7700', 'Ryzen 5 5600G', '4', 'ruimdows', '2');

-- INSERT INTO bd2021.preco_data VALUES ('2021-11-29','30.39','20','Valheim', 'Iron Gate AB','1');
-- INSERT INTO bd2021.preco_data VALUES ('2021-10-29','35.39','10','Valheim', 'Iron Gate AB','1');
-- INSERT INTO bd2021.preco_data VALUES ('2021-9-29','39.99','0','Valheim', 'Iron Gate AB','1');

-- INSERT INTO bd2021.historico_por_loja VALUES ('2021-11-29','30.39','35','20','Valheim', 'Iron Gate AB','1'); --Esses valores serão inseridos/atualizados a partir do algoritmo 


-- SELECT jogo.titulo FROM bd2021.jogo WHERE jogo.desenvolvedora LIKE '%riot%';





