# Springbatch
Link a mi repositorio: https://github.com/Fmejias03/Springbatch.git

## Ejercicio
# Enunciado de la Práctica Spring Batch

## Objetivo

El objetivo de esta práctica es desarrollar una aplicación de procesamiento por batchs, utilizando la programación concurrente para optimizar el rendimiento y minimizar el tiempo de procesamiento.

El programa deberá leer un gran volumen de datos, procesar estos datos de manera eficiente utilizando programación concurrente y, finalmente, almacenar los datos procesados. El contexto de los datos será definido a continuación.

## Contexto

Imaginemos que somos parte de una gran organización que maneja una enorme cantidad de datos de transacciones bancarias. Estos datos son almacenados en archivos planos y se necesita un programa que pueda procesar estos datos por la noche, cuando la base de datos no está bloqueada por otras tareas.

Además, se necesita que el programa pueda reanudar el trabajo en caso de fallos y sea capaz de manejar transacciones. Cada transacción constará de un lote de procesamiento de datos, por ejemplo, una confirmación cada 5,000 inserciones.

El programa deberá estar construido de tal manera que pueda dividirse en pequeñas tareas que se ejecuten en secuencia, generando flujos de trabajo, de modo similar a los microservicios.

## Detalles del Proyecto

El programa deberá implementar las siguientes funciones:

1. Leer los datos del archivo de transacciones bancarias.
2. Procesar los datos utilizando programación concurrente. Esta etapa deberá incluir la validación de datos y el procesamiento y cálculos sobre los datos.
3. Almacenar los datos procesados en la base de datos.
4. Implementar un programador que encadene las tareas y administre las operaciones de flujo.

## Recursos

Para realizar esta práctica, se sugiere utilizar Java con el framework Spring Batch debido a su eficacia para el procesamiento por batchs. No obstante, puedes elegir el lenguaje de programación y las herramientas que prefieras, siempre que permitan la programación concurrente y sean adecuados para el procesamiento por batchs.

Se proporcionará un conjunto de datos de transacciones bancarias ficticias para fines de prueba.

## Entrega

La entrega de la práctica deberá incluir el código fuente de la aplicación, junto con una documentación que explique el diseño del programa, cómo se implementó la programación concurrente, y cómo se gestiona el flujo de trabajo. Además, debes incluir un análisis del rendimiento de la aplicación y cómo la programación concurrente ha optimizado el tiempo de procesamiento.

## Rúbrica para la Práctica de Programación Concurrente

### Funcionalidad del programa (40 puntos)

- El programa puede leer correctamente los datos del archivo de transacciones bancarias (10 puntos).
- El programa procesa los datos correctamente utilizando programación concurrente. Esto incluye la validación de los datos y los cálculos realizados sobre los datos (15 puntos).
- El programa almacena correctamente los datos procesados en la base de datos (10 puntos).
- Implementación correcta del programador que encadena las tareas y administra las operaciones de flujo (5 puntos).

### Concurrencia y Optimización (30 puntos)

- El programa implementa correctamente la programación concurrente para optimizar el tiempo de procesamiento (15 puntos).
- El programa muestra una mejora significativa en el tiempo de procesamiento en comparación con una versión no concurrente del mismo (15 puntos).

### Manejo de errores y robustez (10 puntos)

- El programa puede manejar errores y excepciones de manera adecuada, incluyendo la capacidad de reanudar trabajos erróneos (5 puntos).
- El programa es robusto y puede manejar diferentes casos de entrada, incluyendo datos inválidos o problemáticos (5 puntos).

### Documentación (20 puntos)


This app was created with Bootify.io - tips on working with the code [can be found here](https://bootify.io/next-steps/).
Feel free to contact us for further questions.

## Development

When starting the application `docker compose up` is called and the app will connect to the contained services.
[Docker](https://www.docker.com/get-started/) must be available on the current system.

During development it is recommended to use the profile `local`. In IntelliJ `-Dspring.profiles.active=local` can be
added in the VM options of the Run Configuration after enabling this property in "Modify options". Create your own
`application-local.yml` file to override settings for development.

Lombok must be supported by your IDE. For IntelliJ install the Lombok plugin and enable annotation processing -
[learn more](https://bootify.io/next-steps/spring-boot-with-lombok.html).

In addition to the Spring Boot application, the DevServer must also be started. [Node.js](https://nodejs.org/) has to be
available on the system - the latest LTS version is recommended. On first usage and after updates the dependencies have to be installed:

```
npm install
```

The DevServer can now be started as follows:

```
npm run devserver
```

Using a proxy the whole application is now accessible under `localhost:8081`. All changes to the templates and JS/CSS
files are immediately visible in the browser.

## Build

The application can be built using the following command:

```
mvnw clean package
```

Node.js is automatically downloaded using the `frontend-maven-plugin` and the final JS/CSS files are integrated into the jar.

Start your application with the following command - here with the profile `production`:

```
java -Dspring.profiles.active=production -jar ./target/Springbatch-0.0.1-SNAPSHOT.jar
```

If required, a Docker image can be created with the Spring Boot plugin. Add `SPRING_PROFILES_ACTIVE=production` as
environment variable when running the container.

```
mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=io.bootify/springbatch
```

## Further readings

* [Maven docs](https://maven.apache.org/guides/index.html)  
* [Spring Boot reference](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)  
* [Spring Data JPA reference](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)  
* [Thymeleaf docs](https://www.thymeleaf.org/documentation.html)  
* [Webpack concepts](https://webpack.js.org/concepts/)  
* [npm docs](https://docs.npmjs.com/)  
* [Bootstrap docs](https://getbootstrap.com/docs/5.3/getting-started/introduction/)  
* [Htmx in a nutshell](https://htmx.org/docs/)  
* [Learn Spring Boot with Thymeleaf](https://www.wimdeblauwe.com/books/taming-thymeleaf/)  
