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
  repisa varchar(1),
  familia varchar (20)
  cod_prov varchar (6)
)
alter table producto add codbar_prov varchar (20)

ALTER TABLE producto ADD CONSTRAINT producto_ibfk_1 FOREIGN KEY (nom_prov) REFERENCES provedor (nom_prov) ON DELETE CASCADE ON UPDATE CASCADE

update producto set codbar_prov = '7703486035339' where cod_prov = 'CP0001'

insert into producto values ('CP0001','Galletas','Gamesa','15','30','10','2','3','Quimicos','CC0001','3253634tfd')

select * from producto
CREATE TABLE provedor
	(cod_prov varchar (6) primary key,
	nom_prov varchar (30),
	tel_prov varchar (10),
	email_prov varchar (30),
	dir_prov varchar (30),
	sucursal varchar (30),
	nombrecontacto varchar (30),
	apellidocontacto varchar (30),
	telefonocontacto varchar (10),
	emailcontacto varchar (30))




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
  subtotal varchar(10) NOT NULL,
  igv varchar(40) NOT NULL,
  total varchar(20) NOT NULL,
  fec_fac varchar(20) NOT NULL,
  PRIMARY KEY (num_fac))

  ALTER TABLE factura
  ADD CONSTRAINT factura_ibfk_1 FOREIGN KEY (cod_cli) REFERENCES cliente (cod_cli) ON DELETE CASCADE ON UPDATE CASCADE
  select * from factura

  
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



  Create table Familias (Familia varchar (30))
  insert into Familias values ('Acumuladores'),('Abrazaderas'),('Amortiguadores'),('Filtros'),('Muelles'),('Aceites'),('Quimicos'),('Baleros'),('Calaveras'),('Bandas'),
  ('Balatas'),('Electricos'),('Kits'),('Herramientas'),('Bisuteria'),('Pernos')
  select * from Familias


  CREATE TABLE Usuarios(
	Id_user int PRIMARY KEY IDENTITY(1,1),
	User_nam varchar(20),
	Pass varchar(20),
	Access int,
	Primer_nombre varchar(30),
	segundo_nombre varchar(30),
	Ape_Paterno varchar(30),
	Ape_Materno varchar(30),
	Edad tinyint,
	Sexo varchar(1) ,
	Email varchar(50),
	Tel varchar(13))

	insert into Usuarios values ('admin','admin','1','Ronaldo','Miguel','Alvarez','Landeros','21','M','maicool53@hotmail.com','9828290670')




create table Temas (Id int identity , Tema Varchar (30))
 insert into Temas values ('Nimbus')
Select Tema from Temas where ID = '1' 

Create table TodosTemas (Id int identity, Nombre Varchar(20))
Insert into TodosTemas values ('Nimbus'),
	    ('Black Eye'),
		('Black Moon'),
		('Blue Ice'),
	    ('Blue Moon'),
	    ('Blue Steel'),
	    ('Mauve Metallic'),
	    ('Orange Metallic'),
		('Silver Moon'),
	    ('Simple 2D'),
		('Sky Metallic'),
		('White Vision')

		Select nom_prov from provedor where cod_prov='CC0001'