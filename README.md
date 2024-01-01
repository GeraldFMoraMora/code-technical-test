# BackEnd Prueba Tecnica
**Por: Gerald Mora Mora**
Este repositorio contiene el codigo fuente de la parte correspondiente al backend de la aplicacion de tareas. Este backend se encargará de habilitar los endpoints apiREST para tener comunicación con el frontend de la aplicación, además de establecer la conexion con la base de datos para extraer, modificar y mantener los datos necesarios y requeridos para el funcionamiento, entre otras funciones como validaciones, manejo de excepciones, etc.
## Correr la aplicación:
Primero debe de crearse la base de datos Postgres corriendo el script otorgado con extensión **.sql**.
Ahora, puedes correr esta aplicación en modo desarrollo de la siguiente manera:
```shell script
./mvnw compile quarkus:dev
```
> **_NOTA:_**  Quarkus habilita el siguiente dominio y puerto donde se podrá consumir la aplicación: http://localhost:8080/
## Endpoints:
El consumo directo del API para certificar su funcionamiento, se puede hacer utilizando la herramienta Postman, para la cual, se debe cargar el archivo **.json**, el mismo contiene ejemplos de como se debe consumir cada uno de los endpoints, además de permitir hacer llamados a los mismos en tiempo real.
> Los datos en los cuerpos de la consulta se deben ajustar según la prueba deseada.
###  Aspectos importantes a conocer:
Los valores para representar el genero del usuario del lado del API y la base de datos van a ser:
- **0**: Este valor entero representa el genero femenino.
- **1**: Este valor entero representa el genero masculino.

Para representar el rango de edad, se utilizaran los siguientes valores string:
-**10-17**
-**18-30**
-**31-50**
-**51-64**
-**65+**

Los tres estados que van a tener las tareas y que van a ser interpretados por el frontend van a ser: 
- **todo**, estas son tareas que estan pendientes de realizar.
- **working**, estas son tareas que están en progreso.
- **done**, estas son tareas concluidas.

> **NOTA**: Cualquier otro estado es válido, pero no interpretado por el Frontend. 

En la base de datos existe un campo para cada tarea llamado **is_enabled**, el cual me indicará si la tarea es visible o no por el usuario desde el frontend. Para cambiar este estado desde front-end, es necesario que el usuario oprima el botón de eliminar, para hacerlo desde postman, es necesario que para el **POST** de updateTask se agregue al parametro **is_active** el valor false.

### Endpoints para administración de usuarios:
- **/customer/register**: Este es un **POST** que se encarga de registrar un nuevo usario en el sistema. Su estructura es la siguiente:
```javascript
{ 
	"name":"usernametest1",
	"password":"qwertyy",
	"telephone":"55555555",
	"age_range":"10-18",
	"gender":1,
	"is_active":true 
}
```
- **/customer/login**: Este es un **POST**  que se encarga de encontrar el usuario y contraseña en el sistema:
```javascript
{ 
	"name":"usernametest1",
	"password":"qwertyy"
}
```
- **/customer/findByName/{userName}**: Este es un **GET** que se encarga de encontrar un usuario por su nombre de usuario. En el URL se debe reemplazar {userName} por el nombre de usuario a buscar. Ejemplo:
```shell script
/customer/findByName/usernametest1
```

- ### Endpoints para administración de tareas:
- **/task/getTask/{taskId}/{userId}**: Este es un **GET** que se encarga de encontrar la informacion de una tarea especifica, para ello se debe reemplazar en el URL los valores taskId y userId, que corresponden al id de la tarea y del usuario respectivamente. Ejemplo:
```shell script
/task/getTask/11/14
```
- **/task/getListTask/{userId}**: Este es un **GET** que se encarga de encontrar toda la información de las tareas existentes de un usuario especifíco.  En el URL se debe reemplazar {userId} por el nombre de usuario a buscar. Ejemplo:
```shell script
/task/getListTask/49
```
- **/task/getListStateTask/{userId}/{state}**: Este es un **GET** con el cúal se puede extraer una lista de treas segun el estado especificado, que puede ser todo, working o done. Ejemplo:
```shell script
/task/getListStateTask/16/done
```
- **/task/addTask**: Este es un **POST** con el cúal se puede agregar tareas nuevas. Su estructura es la siguiente:
```javascript
{ 
	"description":"Tarea 8",
	"state":"todo",
	"image_url":"",
	"is_active":true,
	"customer_id":  49
}
```
- **/task/updateTask**: Este es un **POST** con el cúal se puede actualizar alguno de los datos de una tarea existente. Su estrúctura es la siguiente:
 ```javascript
{ 
	"description":"Tarea 8",
	"state":"done",
	"image_url":"",
	"is_active":true,
	"customer_id":  49
}
```
- **/task/deleteTask/{taskId}/{userId}**: Este es un **GET** que se encarga de encontrar una tarea exsitente especifica y la elimina para no ser mostrada en la lista de tareas del frontend (En base de datos, solamente se cambia su estado de habilitado a inhabilitado). Para ello se debe reemplazar en el URL los valores taskId y userId, que corresponden al id de la tarea y del usuario respectivamente. Ejemplo:
```shell script
/task/deleteTask/69/49
```