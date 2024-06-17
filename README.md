# 🚩 Proyecto Lince Java-Server 🚩 
Este proyecto es la parte servidora de la telemetria Lince. Este es capaz de levantar un servidor el cual nos ofrece servicios especificos para la parte cliente-web. Los servicios que nos ofrece son:

> Información de Placa: Nos provee de la información que la placa almacena en la hoja de Google haciendo de intermediario entre Google Sheet y el cliente web.

> Servicios Admin: Algunos servicios como baneos y desbaneos de usuarios en tiempo real también son ofrecidos por la API del servidor.

> Puertos: Son aquellos que nos brindan el numero de puerto TCP o UDP existentes, contienen el número de puerto, el nombre del servicio al cual el puerto está asociado y mediante el tipo de puerto, podremos discriminar objetos equipos STANDARD o IMPRESORA.

# 📃 REQUISITOS 📃
Este proyecto necesita los siguientes apartados para su correcto funcionamiento: 
- ☕ Java 17 (Puede que funcione con versiones superiores).
- 🍃 Framework de Spring Boot versión 3.0.4
- 📚 MySQl, este proyecto utiliza una BBDD MySQL llamada 'proyecto_lince'.

# 🔨 INSTALACIÓN 🔨
Solamente se necesita descargar el proyecto y compilarlo.

# 🔌 API ENDPOINTS 🔌
### ⚠️¡Basados sobre el puerto 8090!⚠️
Nuesta API servidor provee los siguientes endpoints:

### 📗 GET
Este responderá con un listado de usuarios admins, todo aquel no admin simplemente no estará en la lista.
```bash
http://localhost:8090/proyectolince/get/admins
```
### 📗 GET
Este nos responderá con un listado de usuarios baneados, aquellos prohibidos.
```bash
http://localhost:8090/proyectolince/get/banedusers
```
### 📗 GET
Este nos responderá con un listado de todos los chats del chat global.
```bash
http://localhost:8090/proyectolince/get/globalchat
```
### 📗 GET
Este nos responderá con un listado de todos los chats del chat de administracion.
```bash
http://localhost:8090/proyectolince/get/adminchat
```
### 📗 GET
Este nos responderá con un el último registro que envio la placa a la Google Sheet, el cual almacena y procesa en una tabal el servidor Java.
```bash
http://localhost:8090/proyectolince/get/sheetinfo
```
### 📒 POST
Este recibe un parametro query 'chatBubble' el cual tiene el mensaje de texto que se enviará al chat global.
```bash
http://localhost:8090/proyectolince/send/globalchat
```
### 📒 POST
Este recibe un parametro query 'chatBubble' el cual tiene el mensaje de texto que se enviará al chat de administradores.
```bash
http://localhost:8090/proyectolince/send/adminchat
```
### 📒 POST
Este no recibe parametros, es capaz de resetear el chat global a su estado default o limpiarlo.
```bash
http://localhost:8090/proyectolince/reset/globalchat
```
### 📒 POST
Este no recibe parametros, es capaz de resetear el chat de administración a su estado default o limpiarlo.
```bash
http://localhost:8090/proyectolince/reset/adminchat
```
### 📒 POST
Este recibe un parametro query 'email' el cual tiene el email del usuario a banear.
```bash
http://localhost:8090/proyectolince/ban/user
```
### 📒 POST
Este recibe un parametro query 'email' el cual tiene el email del usuario a desbanear.
```bash
http://localhost:8090/proyectolince/unban/user
```
# 🏦 BBDD 🏦
Como se ha comentado en apartados anteriores, se utiliza una BBDD de Mysql llamada 'proyecto_lince'.
### 🔰 Tablas 🔰
Esta base de datos está compuesta por cinco tablas sin contar las tablas autogeneradas por hibernate para el conteo de id autogeneradas. 
(Link imagenes: https://imgur.com/a/fTpEIk6)
### admin_chat y global_chat
>Contienen los chats en formato texto más un identificador autogenerado por Java, por esto existen las tablas acabadas en _seq, las cuales utiliza Java para saber cada X número generar un nuevo identificador, estas tocan, solamente las utiliza Java internamente.

![](https://i.imgur.com/rAf0apo.png)

### admin_users 
>Contiene los email de usuario considerados administradores, además de un Id.
>
![](https://i.imgur.com/mF3NIm8.png)

### baned_users
>Contiene aquellos emails de usuarios prohibidos o baneados, de la misma forma que la anterior.
>
![](https://i.imgur.com/mF3NIm8.png)

### register
>Contiene el último registro actual de la hoja de cálculo con toda la información calculada del vehículo enviada por la placa.
>
![](https://i.imgur.com/l1HbV4o.png)

# ✨ **LICENSE**✨    
>Projecto realizado por David Martinez Flores.

🕵 [️David Martinez Flores](https://github.com/DavidMartinezFlores)
