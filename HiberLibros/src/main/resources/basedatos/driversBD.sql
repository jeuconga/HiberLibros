/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Usuario
 * Created: 14 sept 2021
 */
create table editoriales (
    id int auto_increment primary key,
    nombre_editorial Varchar(100)
);


insert into editoriales values (1,"alfaguara");
insert into editoriales values (2,"anaya");
insert into editoriales values (3,"barco de vapor");

insert into generos values (1, "terror");
insert into libros values(1, "espanyol", "isbn111", "Crimen y castigo", "",4,1,3);
insert into usuarios (apellido, ciudad, direccion, mail, nombre, telef) values ("Perez", "Zgz", "Decilias", "zgz@hiberus.com", "Juan", "666666666");
