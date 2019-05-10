Create database REFACCIONARIABARRIENTOS
USE REFACCIONARIABARRIENTOS

CREATE TABLE cliente
	(cod_cli varchar (6) primary key,
	nom_cli varchar (30),
	ape_cli varchar (30),
	sexo_cli varchar (1),
	clas_cli varchar (1),
	tel_cli varchar (10),
	email_cli varchar (30),
	dir_cli varchar (30))

insert into cliente values ('CC0001','Ronaldo Miguel','Alvarez Landeros','H','A','9828290679','maicool53@hotmail.com','conocida')
insert into cliente values ('CC0002','Andres Antonio','Alvarez Zapata','H','B','9828290679','maicool53@hotmail.com','conocida')

CREATE TABLE producto (
  cod_pro varchar(6)PRIMARY KEY NOT NULL,
  descripcion varchar(30) NOT NULL,
  marca varchar(30) NOT NULL,
  precio varchar(10) NOT NULL,
  precio_venta varchar(10) NOT NULL,
  Stock varchar(10) NOT NULL,  
  estante varchar(1),
  repisa varchar(1)
)
insert into producto values ('CP0001','Galletas','Gamesa','15','30','8','2','3')


CREATE TABLE provedor
	(cod_prov varchar (6) primary key,
	nom_prov varchar (30),
	tel_prov varchar (10),
	email_prov varchar (30),
	dir_prov varchar (30))




CREATE TABLE boleta (
  num_bol varchar(10) PRIMARY KEY NOT NULL,
  cod_cli varchar(6) NOT NULL,
  pre_tot varchar(10) NOT NULL,
  fecha varchar(15) NOT NULL)
ALTER TABLE boleta
  ADD CONSTRAINT boleta_ibfk_1 FOREIGN KEY (cod_cli) REFERENCES cliente (cod_cli) ON DELETE CASCADE ON UPDATE CASCADE

CREATE TABLE detalleboleta (
  num_bol varchar(10) NOT NULL,
  cod_pro varchar(6) NOT NULL,
  des_pro varchar(30) NOT NULL,
  cant_pro varchar(3) NOT NULL,
  pre_unit varchar(10) NOT NULL,
  pre_venta varchar(10) NOT NULL) 

  ALTER TABLE detalleboleta
  ADD CONSTRAINT detalleboleta_ibfk_1 FOREIGN KEY (num_bol) REFERENCES boleta (num_bol) ON DELETE CASCADE ON UPDATE CASCADE
  ALTER TABLE detalleboleta
  ADD CONSTRAINT detalleboleta_ibfk_2 FOREIGN KEY (cod_pro) REFERENCES producto (cod_pro) ON DELETE CASCADE ON UPDATE CASCADE



  
CREATE TABLE factura (
  num_fac varchar(8) NOT NULL,
  cod_cli varchar(6) NOT NULL,
  ruc_cli varchar(11) NOT NULL,
  subtotal varchar(10) NOT NULL,
  igv varchar(40) NOT NULL,
  total varchar(20) NOT NULL,
  fec_fac varchar(20) NOT NULL,
  PRIMARY KEY (num_fac))

  ALTER TABLE factura
  ADD CONSTRAINT factura_ibfk_1 FOREIGN KEY (cod_cli) REFERENCES cliente (cod_cli) ON DELETE CASCADE ON UPDATE CASCADE

  
CREATE TABLE detallefactura (
  num_fac varchar(8) NOT NULL,
  cod_pro varchar(6) NOT NULL,
  des_pro varchar(30) NOT NULL,
  cant_pro varchar(3) NOT NULL,
 pre_unit varchar(10) NOT NULL,
  pre_tot varchar(10) NOT NULL)
  ALTER TABLE detallefactura
  ADD CONSTRAINT detallefactura_ibfk_1 FOREIGN KEY (num_fac) REFERENCES factura (num_fac) ON DELETE CASCADE ON UPDATE CASCADE
  ALTER TABLE detallefactura
  ADD CONSTRAINT detallefactura_ibfk_2 FOREIGN KEY (cod_pro) REFERENCES producto (cod_pro) ON DELETE CASCADE ON UPDATE CASCADE 

