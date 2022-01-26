-- DROP SCHEMA bd2021 CASCADE;

CREATE SCHEMA bd2021;

CREATE TABLE bd2021.loja(
	id SERIAL,
	nome VARCHAR(100),
	loja_link VARCHAR(300),
	loja_img VARCHAR(300),
	CONSTRAINT pk_loja PRIMARY KEY(id)
);

CREATE TABLE bd2021.jogo(
	id SERIAL,
	titulo VARCHAR(100) NOT NULL,
	desenvolvedora VARCHAR(100) NOT NULL,
	categoria VARCHAR(100),
	descricao text,
	publicadora VARCHAR(100),
	ano_publicacao VARCHAR(100),
	cpu VARCHAR(100),
	gpu VARCHAR(100),
	memoria_ram VARCHAR(100),
	so VARCHAR(100),
	armazenamento VARCHAR(100),
	image VARCHAR(300),
	CONSTRAINT pk_jogo PRIMARY KEY(id)
);


CREATE TABLE bd2021.preco_data(
	data_registro DATE,
	preco NUMERIC(6,2),
	porcentagem_promo INT,
	jogo_id INT NOT NULL,
	loja_id INT NOT NULL,
	CONSTRAINT fk_jogo_preco_data FOREIGN KEY(jogo_id)
	REFERENCES bd2021.jogo(id),
	CONSTRAINT fk_loja_preco_data FOREIGN KEY(loja_id)
	REFERENCES bd2021.loja(id),
	CONSTRAINT pk_preco_data PRIMARY KEY(jogo_id, loja_id, data_registro)
);

CREATE TABLE bd2021.historico(
	
	data_menor_preco DATE,
	menor_preco NUMERIC(6,2),
	media_preco NUMERIC(6,2),
	maior_promo INT,
	jogo_id INT NOT NULL,
	loja_id INT NOT NULL,
 	CONSTRAINT fk_preco_data_historico FOREIGN KEY(jogo_id, loja_id, data_menor_preco)
	REFERENCES bd2021.preco_data(jogo_id, loja_id, data_registro),
	CONSTRAINT pk_historico PRIMARY KEY(jogo_id, loja_id)
);


-- INSERT INTO bd2021.loja VALUES (DEFAULT,'steam','https://store.steampowered.com/?l=portuguese','assets/images/logos/steam_logo.png');
-- INSERT INTO bd2021.loja VALUES (DEFAULT,'nuuvem','https://www.nuuvem.com','assets/images/logos/nuuvem_logo.png');
-- INSERT INTO bd2021.loja VALUES (DEFAULT,'epicgames','https://www.epicgames.com/store/pt-BR/','assets/images/logos/epicgames_logo.png');

-- INSERT INTO bd2021.jogo VALUES (DEFAULT,'Valheim','Iron Gate AB','aventura','joguinho legal','tantofaz', '2018-10-10','i7-1100', 'Ryzen 5 5600G', '4', 'ruimdows', '2');
-- INSERT INTO bd2021.jogo VALUES (DEFAULT,'MUK','Iron Gate AB','aventura','joguinho legal','tantofaz','2018-10-10', 'i7-1100', 'Ryzen 5 5600G', '4', 'ruimdows', '2');
-- INSERT INTO bd2021.jogo VALUES (DEFAULT,'Call of duty','Iron Gate AB','aventura','joguinho legal','tantofaz','2018-10-10', 'i7-1100', 'Ryzen 5 5600G', '4', 'ruimdows', '2');

-- INSERT INTO bd2021.preco_data VALUES ('2021-11-29','30.39','20','1','1');
-- INSERT INTO bd2021.preco_data VALUES ('2021-10-29','35.39','10','2','1');
-- INSERT INTO bd2021.preco_data VALUES ('2021-9-29','39.99','0','3','1');

-- INSERT INTO bd2021.historico VALUES ('2021-11-29','30.39','35','20','1', '1'); --Esses valores serão inseridos/atualizados a partir do algoritmo 


-- SELECT jogo.titulo FROM bd2021.jogo WHERE jogo.desenvolvedora LIKE '%riot%';

-- SELECT * FROM bd2021.loja
-- SELECT * FROM bd2021.jogo
-- SELECT * FROM bd2021.preco_data
-- SELECT * FROM bd2021.historico





