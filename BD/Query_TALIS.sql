Create database TALIS

go

Use TALIS

go

-- Creación de tablas para la dirección del cliente
-- Primefaces , formularios, tablas, mensajes.
-- JASPER, Jaspersoft (complicado)
-- JSP
Create Table INST_PROVINCIA (
	COD_PROVINCIA numeric(2) not null,
	DSC_CORTA_PROVINCIA varchar(15),
	DSC_PROVINCIA varchar(255),
	LOG_ACTIVO numeric(1),	
);

Create Table INST_CANTON (
	COD_PROVINCIA numeric(2) not null,
	COD_CANTON numeric(2) not null,
	DSC_CANTON varchar(255),
	LOG_ACTIVO numeric(1),
);

Create Table INST_DISTRITO (
	COD_PROVINCIA numeric(2) not null,
	COD_CANTON numeric(2) not null,
	COD_DISTRITO numeric(2) not null,
	DSC_DISTRITO varchar(255),
	LOG_ACTIVO numeric(1),
);

Create Table INST_BARRIO(
	COD_PROVINCIA numeric(2) not null,
	COD_CANTON numeric(2) not null,
	COD_DISTRITO numeric(2) not null,
	COD_BARRIO numeric(3) not null,
	DSC_BARRIO varchar(255),
	LOG_ACTIVO numeric(1)
);


Create Table INST_DIRECCION(
    COD_DIRECCION int identity(1,1) not null,
	COD_PROVINCIA numeric(2) not null,
	COD_CANTON numeric(2)not null,
	COD_DISTRITO numeric(2) not null,
	COD_BARRIO numeric(3) not null,
	DSC_DIRECCION varchar(250),
	DIR_PRINCIPAL bit,
	COD_USUARIO varchar(20) not null
);

-- join a la tabla Persona
-- Creación de la tabla USUARIO
Create Table USUARIO(
	COD_USUARIO varchar(20) not null,
	NOMBRE varchar(250) not null,
	PRIMER_APELLIDO varchar(250) not null,
	SEGUNDO_APELLIDO varchar(250) not null,
	TELEFONO varchar(10) not null,
	EMAIL varchar(30) not null,
	CLAVE varchar(12) not null,
	COD_ESTADO int not null, -- DEFAULT 1- PENDIENTE, 2- ACTIVA, 3- RECHAZADA
	COD_ROL int not null
);

-- Creación de tabla horario
Create Table HORARIO_ENTREGA(
	COD_HORARIO int identity(1,1) not null,
	FECHA datetime,
	COD_USUARIO varchar(20) not null
);

Create Table ESTADO_USUARIO(
	COD_ESTADO int identity(1,1),
	LOG_ACTIVO int
);

-- Creación de tabla rol
Create Table ROL(
	COD_ROL int identity(1,1) not null,
	TIPO_ROL varchar(20) not null -- Admins, cliente, despachador
);

-- Creación de tabla para productos
Create Table PRODUCTO(
	COD_PRODUCTO int identity(1,1),
	FOTOGRAFIA varchar(500) not null,
	NOMBRE varchar(50) not null,
	DSC_PRODUCTO varchar(250),
	CANTIDAD_MINIMA int not null,
	CANTIDAD_EXISTENCIA int not null,
	LOG_ACTIVO int not null,
	PRECIO float not null
);

-- Creación de tabla para órdenes
Create Table ORDEN(
	COD_ORDEN int identity(1,1) not null,
	COD_USUARIO varchar(20) not null,
	COD_DIRECCION int not null,
	COD_HORARIO int not null,
	LOG_ACTIVO int not null, -- Defautl 1- Pendiente, 2- Realizado, 3- Finalizado
	FECHA_PEDIDO datetime, -- Se guarda con el sistema
);

-- Creación tabla para el detalle de la orden
Create Table DETALLE_ORDEN(
	COD_DETALLE int identity(1,1) not null,
	COD_ORDEN int not null,
	COD_PRODUCTO int not null,
	CANTIDAD int,
	MONTO_UNIDAD float,
	LOG_ACTIVO int
);

-- Creación de tabla para el estado de la orden
Create Table ESTADO_ORDEN(
	LOG_ACTIVO int not null,
	DSC_ESTADO varchar(20) not null
);

--Creación de Tabla Factura
Create Table FACTURA(
	COD_FACTURA int identity(1,1) not null,
	SUBTOTAL float not null,
	POS_DESCUENTO float,
	IVA float not null,
	TOTAL float not null,
	TIPO_PAGO varchar(20) not null,
	FECHA datetime not null, -- Se guarda con el sistema
	COD_ORDEN int not null,
	COD_DETALLE_FACTURA int not null
);

Create Table DETALLE_FACTURA(
    COD_DETALLE_FACTURA int identity(1,1) not null,
	IMPUESTO_CU float not null,
	MONTO_DESCUENTO float not null,
	TIPO_PAGO varchar(50) not null,
	TOTAL_FACTURA float not null,
	TOTAL_DESCUENTO float not null,
	TOTAL_IMPUESTO float not null
);

--Creación de Tabla Despacho
Create Table DESPACHO(
	COD_DESPACHO int identity(1,1) not null,
	FECHA_ENVIO datetime not null,
	HORA_ENVIO datetime not null,
	OBSERVACION varchar(300) null,
	LOG_ACTIVO int not null,
	COSTO float not null,
	COD_ENTREGA int not null,
	COD_ORDEN int not null
);

--Creación de Tabla Tipo Entrega
Create Table TIPO_ENTREGA(
	COD_ENTREGA int identity(1,1) not null,
	DSC_ENTREGA varchar(30) not null
);


-- Llaves primarias

-- Dirección
Alter table INST_PROVINCIA add constraint INST_PROVINCIA_PK primary key (COD_PROVINCIA);
Alter Table INST_CANTON add Constraint INST_CANTON_PK primary key(COD_PROVINCIA, COD_CANTON);
Alter Table INST_DISTRITO add Constraint INST_DISTRITO_PK primary key (COD_PROVINCIA, COD_CANTON, COD_DISTRITO);
Alter table INST_BARRIO add constraint INST_BARRIO_PK primary key(COD_PROVINCIA, COD_CANTON, COD_DISTRITO, COD_BARRIO);
Alter table INST_DIRECCION add constraint INST_DIRECCION_PK primary key(COD_DIRECCION);

-- Autoregistro
Alter Table USUARIO add constraint USUARIO_PK primary key(COD_USUARIO);
Alter Table HORARIO_ENTREGA add constraint HORARIO_ENTREGA_PK primary key(COD_HORARIO);
Alter Table ESTADO_USUARIO add constraint ESTADO_CUENTA_PK primary key(COD_ESTADO);
Alter table ROL add constraint ROL_PK primary key(COD_ROL);

-- Producto
Alter Table PRODUCTO add constraint PRODUCTO_PK primary key(COD_PRODUCTO);

-- Órden
Alter Table ORDEN add constraint ORDEN_PK primary key(COD_ORDEN);
Alter Table DETALLE_ORDEN add constraint DETALLE_ORDEN_PK primary key(COD_DETALLE);
Alter Table ESTADO_ORDEN add constraint ESTADO_ORDEN_PK primary key(LOG_ACTIVO);

-- Factura
Alter table FACTURA add constraint FACTURA_PK primary key(COD_FACTURA);
Alter table TIPO_ENTREGA add constraint TIPO_ENTREGA_PK primary key(COD_ENTREGA);
Alter table DETALLE_FACTURA add constraint DETALLE_FACTURA_PK primary key(COD_DETALLE_FACTURA);


-- Despacho
Alter Table DESPACHO add constraint DESPACHO_PK primary key(COD_DESPACHO);

-- Llaves foráneas

-- Direcciones
Alter Table INST_CANTON add Constraint INST_CANTON_FK foreign key(COD_PROVINCIA) references INST_PROVINCIA(COD_PROVINCIA);
Alter Table INST_DISTRITO add Constraint INST_DISTRITO_FK foreign key (COD_PROVINCIA, COD_CANTON) references INST_CANTON(COD_PROVINCIA, COD_CANTON);
Alter table INST_BARRIO add constraint INST_BARRIO_FK foreign key(COD_PROVINCIA, COD_CANTON, COD_DISTRITO) references INST_DISTRITO(COD_PROVINCIA, COD_CANTON, COD_DISTRITO);
Alter table INST_DIRECCION add constraint INST_DIRECCION_FK foreign key(COD_PROVINCIA, COD_CANTON, COD_DISTRITO, COD_BARRIO) references INST_BARRIO(COD_PROVINCIA, COD_CANTON, COD_DISTRITO, COD_BARRIO);
Alter table INST_DIRECCION add constraint INST_DIRECCION_USUARIO_FK foreign key(COD_USUARIO) references USUARIO(COD_USUARIO);

-- Autoregistro
Alter Table HORARIO_ENTREGA add constraint HORARIO_ENTREGA_FK foreign key(COD_USUARIO) references USUARIO(COD_USUARIO);
Alter Table USUARIO add constraint ESTADO_USUARIO_FK foreign key(COD_ESTADO) references ESTADO_USUARIO(COD_ESTADO);
Alter Table USUARIO add constraint USUARIO_ROL_FK foreign key(COD_ROL) references ROL(COD_ROL);

-- Orden
Alter Table ORDEN add constraint ORDEN_USUARIO_FK foreign key(COD_USUARIO) references USUARIO(COD_USUARIO);
Alter Table ORDEN add constraint ORDEN_DIRECCION_FK foreign key(COD_DIRECCION) references INST_DIRECCION(COD_DIRECCION);
Alter Table ORDEN add constraint ORDEN_HORARIO_FK foreign key(COD_HORARIO) references HORARIO_ENTREGA(COD_HORARIO);
Alter Table ORDEN add constraint ORDEN_ESTADO_FK foreign key(LOG_ACTIVO) references ESTADO_ORDEN(LOG_ACTIVO);
Alter table DETALLE_ORDEN add constraint DETALLE_ORDEN_FK foreign key(COD_ORDEN) references ORDEN(COD_ORDEN);
Alter Table DETALLE_ORDEN add constraint DETALLE_PRODUCTO_FK foreign key(COD_PRODUCTO) references PRODUCTO(COD_PRODUCTO);


-- Factura
Alter Table FACTURA add constraint FACTURA_FK foreign key(COD_ORDEN) references ORDEN(COD_ORDEN);
Alter Table FACTURA add constraint DETALLE_FACTURA_FK foreign key(COD_DETALLE_FACTURA) references DETALLE_FACTURA(COD_DETALLE_FACTURA);

-- Despacho
Alter Table DESPACHO add constraint DESPACHO_ENTREGA_FK foreign key(COD_ENTREGA) references TIPO_ENTREGA(COD_ENTREGA);
Alter Table DESPACHO add constraint DESPACHO_ORDEN_FK foreign key(COD_ORDEN) references ORDEN(COD_ORDEN);