create table proyectos(

	id int primary key,
	nombre varchar(100) not null,
	dias_estimados int not null

);

create table tecnologias(

	id int primary key,
	nombre varchar(50) not null,
	categoria varchar(50) not null

);


create table proyecto_tecnologia(

	pt_proyecto_id_fk int not null,

	pt_tecnologia_id_fk int not null,

	version varchar(20) not null,

	constraint proyecto_fk
	foreign key(pt_proyecto_id_fk)
	references proyectos(id),

	constraint tecnologia_fk
	foreign key(pt_tecnologia_id_fk)
	references tecnologias(id),

	constraint proyecto_tecnologia_pk
	primary key(
		pt_proyecto_id_fk,
		pt_tecnologia_id_fk
	)
);


insert into proyectos(
	id,
	nombre,
	dias_estimados
)
values
(1,'Sistema Bancario',180),
(2,'Sistema Escolar',120),
(3,'Tienda Virtual',90),
(4,'Sistema Hospitalario',240);


insert into tecnologias(
	id,
	nombre,
	categoria
)
values
(1,'Java','Backend'),
(2,'Spring Boot','Framework'),
(3,'PostgreSQL','Base de Datos'),
(4,'React','Frontend');



insert into proyecto_tecnologia(
	pt_proyecto_id_fk,
	pt_tecnologia_id_fk,
	version
)
values

(1,1,'17'),
(1,2,'3.5'),
(1,3,'15'),

(2,1,'17'),
(2,2,'3.4'),
(2,3,'14'),

(3,2,'3.5'),
(3,3,'15'),
(3,4,'18'),

(4,1,'21'),
(4,2,'3.5');



select p.nombre,
	   t.nombre,
	   t.categoria,
	   pt.version
from proyecto_tecnologia pt
inner join proyectos p
on pt.pt_proyecto_id_fk = p.id
inner join tecnologias t
on pt.pt_tecnologia_id_fk = t.id;


select t.nombre,
	   t.categoria,
	   pt.version
from proyecto_tecnologia pt
inner join tecnologias t
on pt.pt_tecnologia_id_fk = t.id
inner join proyectos p
on pt.pt_proyecto_id_fk = p.id
where p.nombre = 'Sistema Bancario';


select p.nombre,
	   p.dias_estimados,
	   pt.version
from proyecto_tecnologia pt
inner join proyectos p
on pt.pt_proyecto_id_fk = p.id
where pt.pt_tecnologia_id_fk = 2;


select p.nombre,
	   p.dias_estimados,
	   t.nombre
from proyecto_tecnologia pt
inner join proyectos p
on pt.pt_proyecto_id_fk = p.id
inner join tecnologias t
on pt.pt_tecnologia_id_fk = t.id
order by p.dias_estimados desc;


select t.nombre,
	   count(*) as total_proyectos
from proyecto_tecnologia pt
inner join tecnologias t
on pt.pt_tecnologia_id_fk = t.id
group by t.nombre;