##Introduccion
Este proyecto ha sido creado como parte del proceso de seleccion para vacante en la empresa Intcomex.

La implementación siguió las mejores prácticas de ingeniería de software y dividió el alcance de cada clase según su propósito.

##Tecnologias utilizadas
- Java 8
- Maven
- SpringBoot
- Eureka (Netflix)
- MySQL
- Heroku

##Estructura del proyecto
1. Paquete /models.entity
    * Contiene todas las entidades, asi como propiedades y estructuras para la generacion de la base de datos
2. Paquete /repositories
    * Contiene los repositorios con las operaciones necesarias para el manejo de los datos
3. Paquete /services
    * Contiene las clases y metodos necesarios para la conexión con los repositorios y/o lógica de negocio
4. Paquete /controllers
    * Contiene la logica necesaria para exponer los endpoints por los cuales nos conectaremos a nuestra API
5./ Paquete /test
    * Contiene los tests unitarios del proyecto

##Endpoints para pruebas
1. Crear Categoría **( POST https://store-intcomex.herokuapp.com/Category/)**
    * Ejemplo de request: 
    * `{
      "categoryName" : "Servidores",
      "description" : "Categoria de servidores",
      "picture" : "https://concepto.de/wp-content/uploads/2019/05/servidor-e1557165670831.jpg"
      }`
2. Crear Producto **( POST https://store-intcomex.herokuapp.com/Product/)**
    * Ejemplo de request:
    * `{
      "productName" : "Producto de prueba 1",
      "supplierId" : 4,
      "categoryId" : 24 ,
      "quantityPerUnit" : 50,
      "unitPrice" : 1500.00,
      "unitsInStock" : 30,
      "unitsInOrder" : 3,
      "reorderLevel" : "Test",
      "discontinued" : false
      }`
    * Son necesarios los parametros ?pageNumber=1&pageSize=3 para la obtención de los resultados (como fue solicitado)
3. Listar productos **( GET https://store-intcomex.herokuapp.com/Products/)**
4. Listar productos por ID **(GET https://store-intcomex.herokuapp.com/Products/id/)**

##Despliegue Online
Para desplegar la aplicación he utilizado el plan de alojamiento gratuito de Heroku
el cual incluye una funcion de conexion directa con github.
Heroku implementará y actualizará automáticamente la aplicación cuando se realice un nuevo commit en el branch [main] del repositorio.

Podria tomarse como un proceso de CI.

###Assets
En la carpeta /assets pueden encontrar el archivo exampleProducts.json con 100.000 registros para pruebas

###Recomendaciones
Para las entidades Supplier y Category se crearon endpoints para manejar el CRUD de las mismas (ya que estan relacionadas con la entidad principal Product), por el momento las categorias Servidores y Cloud estan creadas en la BD (e incluidas en los registros del archivo json de ejemplo) pero si se requiere crear mas tambien se puede hacer, solo recordar que al crear un producto se debe colocar el id del supplier y category deseado (los cuales deben existir en BD, sino se retorna mensaje de error).
Para esto en la carpeta /assets/postmanCollections se encuentran las colecciones que pueden descargarse e importar en postman para probar lo antes mencionado.

###Adicional
Se creó un servidor Eureka para la visualizacion del estatus del servicio, asi como información las instacias desplegadas. Para acceder al servidor eureka solo ingrese al link https://eureka-server-intcomex.herokuapp.com/

El proyecto del servidor eureka tambien se encuentra desplegado directo desde github (https://github.com/salamancacm/eureka-intcomex) a heroku. 