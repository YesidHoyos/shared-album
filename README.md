# shared-album
Microservicio para la gestión de los albumes compartidos de la red social woloxgram

## Tabla de contenido
* [Tecnologías](#tecnologías)
* [Prerequisitos](#prerequisitos)
* [Instalación](#instalación)
* [Uso](#uso)

## Tecnologías
Proyecto creado con:
* Versión Java: 1.8
* Versión Spring-boot: 2.4.5
* Versión Maven: 3.6.1
* Eclipse sts

## Prerequisitos
* Versión Java 8 instalada
* Versión Maven: 3.6.1 or later instalada
* [Base de datos MongoDB](#mongo)

## Mongo
puedes correr MongoDB usando Docker: ```docker run -d -p 27017:27017 --network woloxgram-network --ip 172.168.0.20 --name=mongodb mongo```. Validar primero que se haya creado la red, mas abajo se explica

## Instalación
Para correr este proyecto en local, ejecuta los siguientes comandos usando maven y java

```
$ cd /{workdir}/shared-album
$ mvn clean install
$ cd target
$ java -jar shared-album-0.0.1-SNAPSHOT.jar
```
Para correr este proyecto en docker, ejecuta los siguientes comandos:
```
$ cd /{workdir}/shared-album
$ mvn clean install
$ (opcional, si la red aún no ha sido creada)docker network create --subnet 172.168.0.1/24 --gateway 172.168.0.2 -d bridge woloxgram-network
$ docker build -t shared-album-microservice:1.0.0 .
$ docker run -d -p 9090:9090 --name SharedAlbumMicroservice --network woloxgram-network --ip 172.168.0.21 shared-album-microservice:1.0.0
```

## Uso
En el servicio shared-album puede encontrar los siguientes endpoints
* **Guardar album compartido-** registra un album compartido con otros usuarios.\
Puedes crear una petición tipo POST a la siguiente url: 
* Si estas en local o ejecutando en docker  ```http://localhost:9090/sharedalbums```
mandando como body en la peticón el siguiente json de ejemplo: ```{"albumId": 1,"ownerUserId": 1,	"guestUsers": [{"userId": 5,"isWriter": true,"isReader": true}]}```
* Si vas a consumirlo de producción  ```http://woloxgramsharedalbum.us-east-2.elasticbeanstalk.com/sharedalbums```
mandando como body en la peticón el siguiente json de ejemplo: ```{"albumId": 1,"ownerUserId": 1,	"guestUsers": [{"userId": 5,"isWriter": true,"isReader": true}]}```

* **Actualizar permisos de usuario-** actualiza los permisos de un usuario sobre un album compartido.\
Puedes crear una petición tipo PUT a la siguiente url: 
* Si estas en local o ejecutando en docker  ```http://localhost:9090/sharedalbums/{albumId}```
mandando como body en la peticón el siguiente json de ejemplo: ```{"userId": 5, "isWriter": true,	"isReader": false}```
* Si vas a consumirlo de producción  ```http://woloxgramsharedalbum.us-east-2.elasticbeanstalk.com/sharedalbums/{albumId}```
mandando como body en la peticón el siguiente json de ejemplo: ```{"userId": 5, "isWriter": true,	"isReader": false}```

* **Obtener usuario por album y tipo de permiso -** obtiene todos los usuarios con el permiso dado para el album dado.\
Puedes crear una petición tipo GET a la siguiente url: 
* Si estas en local o ejecutando en docker  ```http://localhost:9090/sharedalbums/{albumId}?access={access}```
* Si vas a consumirlo de producción  ```http://woloxgramsharedalbum.us-east-2.elasticbeanstalk.com/sharedalbums/{albumId}?access={access}```
