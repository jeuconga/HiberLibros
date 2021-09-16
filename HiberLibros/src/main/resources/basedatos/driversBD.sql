/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Usuario
 * Created: 14 sept 2021
 */

insert into editoriales values (1,"alfaguara");
insert into editoriales values (2,"anaya");
insert into editoriales values (3,"barco de vapor");
insert into editoriales values (4,"titum mas");


insert into generos values (1, "terror");
insert into generos values (2, "Sci-Fi");
insert into generos values (3, "Manuales Tecnicos");
insert into generos values (4, "Diccionarios/Enciclopedias");
insert into generos values (5, "Cuentos infantiles");


insert into autores values (1,"Perez Reverte","un autor muy chulo","Arturo")
insert into autores values (2,"Dahl","un autor de cuentos","Ronalh")
insert into autores values (3,"hemanos Green","un autor de cuentos","")
insert into autores values (4,"ojuelos gomez","autor de tratados derecho descanso","Francisco jose")

insert into libro_autor values (1,1,1) # reberte - capital alatriste
insert into libro_autor values (2,1,2) # reberte - la reina del sur
insert into libro_autor values (3,2,3) # dahl - harry y la fabrica
insert into libro_autor values (4,2,4) # dahl - matilda
insert into libro_autor values (5,2,5) # dahl - las brujas
insert into libro_autor values (6,4,6) # Francisco José Ojuelos Gómez - derecho al silencio
insert into libro_autor values (7,5,6) # Bartomeu Rosselló i Boeres - derecho al silencio

insert into libros (id, idioma, isbn,titulo, uri_portada,id_genero, id_editorial, valoracion_libro) values(1, "espanyol", "444441", "El capitan alatriste", "http://www.google.com",2,1, 4.6);
insert into libros (id, idioma, isbn,titulo, uri_portada,id_genero, id_editorial, valoracion_libro) values(2, "espanyol", "111112", "la reina del sur", "http://www.google.com",2,3, 3.2);
insert into libros (id, idioma, isbn,titulo, uri_portada,id_genero, id_editorial, valoracion_libro) values(3, "espanyol", "222223", "harry y la fabrica de chocolate", "http://www.yahoo.com",5,4, 2 );
insert into libros (id, idioma, isbn,titulo, uri_portada,id_genero, id_editorial, valoracion_libro) values(4, "espanyol", "77774", "Matilda", "http://www.yahoo.com",5,2, 3.5);
insert into libros (id, idioma, isbn,titulo, uri_portada,id_genero, id_editorial, valoracion_libro) values(5, "espanyol", "84213215", "las brujas", "http://www.yahoo.com",5,3, 2.1);
insert into libros (id, idioma, isbn,titulo, uri_portada,id_genero, id_editorial, valoracion_libro) values(6, "espanyol", "84213215", "derecho al silencio", "http://www.yahoo.com",3,4, 4.1);

insert into usuarios values (1, "Perez", "Zgz", "calle claves", "zgz@hibes.com", "Juan", "666666666");
insert into usuarios values (2, "sanchez", "Zgz", "calle azucenas", "zrz@hibeus.com", "Luis", "7777777");
insert into usuarios values (3, "williams", "Zgz", "Plaza Decilias", "ztz@hibrus.com", "manolo ", "88888888");
insert into usuarios values (4, "Lopez", "Zgz", "c\Decilias", "zyz@hberus.com", "Lolo no manolo", "99999999");








create table editoriales (
    id int auto_increment primary key,
    nombre_editorial Varchar(100)
);




