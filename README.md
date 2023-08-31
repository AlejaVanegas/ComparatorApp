# Comparador de Precios

## Introducción

Con los avances tecnológicos actuales, las compras en línea se han convertido en una parte integral de las actividades diarias en los hogares, proporcionando una manera eficiente de ahorrar tanto tiempo como dinero. Además, estas acciones requieren una planificación apropiada y una correcta identificación para lograr un ahorro sustancial.

Teniendo esto en cuenta, surge la iniciativa de crear un software de pruebas como proyecto personal. El objetivo de este software es acceder a tres plataformas en línea que operan en Colombia y se dedican principalmente a la venta de productos electrónicos. El software desempeña la función de llevar a cabo búsquedas rápidas de productos específicos y presenta los precios recopilados de cada página en pesos colombianos.

Este software simplifica y agiliza el proceso de búsqueda de un producto en particular, eliminando la necesidad de acceder manualmente a cada sitio web. Al reducir los tiempos de carga de las páginas y proporcionar una lista concisa de precios, permite identificar rápidamente las opciones más atractivas para posteriormente continuar con una compra manual.

## Tabla de Contenidos

- [Objetivos](#objetivos)
- [Características Principales](#características-principales)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Paso a Paso del Código](#paso-a-paso-del-código)
- [Corrección de Errores](#corrección-de-errores)

## Objetivos

### Objetivo General:
Construir un mecanismo que permita la comparación de precios entre productos de diferentes páginas web.

### Objetivos específicos:
1. Generar un diagrama sobre el proceso de análisis de los elementos para el mecanismo que permita la comparación de precios.
2. Realizar una revisión y posterior selección de las páginas que serán utilizadas para la comparación.
3. Revisar el código fuente, para la obtención de etiquetas que serán implementadas dentro del código.
4. Generar un código con la herramienta Selenium, que permita la recopilación de información sobre los precios de algunos productos en venta dentro de las páginas visitadas.
5. Desarrollar pruebas que permitan comprobar la eficacia del código y encontrar defectos.
6. Construir un documento que respalde la información del mecanismo que fue creado.

## Características Principales
- Permite generar búsquedas de cualquier artículo del tipo electrónico en cada tienda.
- Captura el nombre con algunas de sus características y precio en pesos colombianos.
- Genera un aviso en caso de no encontrar el artículo como ítem principal en la página o en caso de fallos en el ingreso de la página.

Para información detallada sobre los procesos que realiza el software, ver [Anexo 1](#anexos).

## Tecnologias Utilizadas
- Lenguaje de programación: Java.
- Herramienta de automatización: Selenium.
<<<<<<< HEAD
- Web Driver:  Chrome Versión 114.0.5735.90.
=======
- Web Driver: Chrome Versión 114.0.5735.90.
>>>>>>> 4ed6ffe0fed4bfe1297938af9b1ab2ba53af525a

## Paso a Paso del Código

## Construcción de la carpeta del proyecto
Se crea una carpeta específica para el proyecto, de manera que pueda ser seleccionada por el IDE para generar los archivos base.

## Nuevo proyecto
Para este proyecto, se utilizó el IDE Visual Code Studio. Para la construcción de un nuevo proyecto se especificó el uso del arquetipo "Maven archetype quickstart" 1.4. Se generó el nombre del proyecto y se seleccionó la carpeta previamente creada.

Una vez generado el proyecto, el IDE solicitará los siguientes campos, que serán nombrados a preferencia del usuario desde la terminal:
- Group id
- Artifact id
- Version
- Package

## Modificación del archivo pom.xml
Al seleccionar Maven como arquetipo del proyecto, se deben incluir las dependencias correspondientes para el correcto funcionamiento de la herramienta de automatización. En este caso, la dependencia para configurar Selenium (herramienta de automatización seleccionada) fue:
```xml
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>3.11.0</version>
</dependency>
```

## Inicialización del Webdriver
Se realiza la descarga del Web Driver de Google Chrome acorde a la versión más actualizada tanto de Chrome como del Web Driver para generar el entorno donde se realizará la prueba. Este se coloca dentro de la carpeta previamente construida del proyecto.

Información sobre el Chrome Driver utilizado:
- Página de descarga del Chrome driver: [Chrome driver](link_de_descarga_del_chrome_driver)
- Versión: 114.0.5735.90.
- Compatible con: Chrome versión 114.

Para inicializar este Chromedriver dentro del `main` del código, se establece la propiedad del sistema, colocando el nombre que se le quiere asignar y la dirección donde se encuentra el archivo descargado. Así mismo, se instala el WebDriver que permitirá controlar las pruebas con los siguientes pasos del código.

```java
System.setProperty("webdriver.chrome.driver", "dirección\\de\\la\\carpeta");
WebDriver driver = new ChromeDriver();
```

## Construcción de una clase Producto
Para capturar las tres características específicas de los productos que se van a buscar con el proyecto, se genera una clase "Producto" con los atributos:
- Nombre
- Precio
- Página

Se generan los respectivos constructores, getters y setters, para su correcta manipulación dentro del código.

## Asignación de variables e inicialización de colecciones previas a la automatización del proyecto
Para este proyecto en particular se utilizó:
- Contador de páginas, como un `int` que se inicializa en 0.
- Palabra clave, como un `String` que se modifica acorde a las búsquedas que se realizarán.
- `ArrayList`, para la organización de los productos y sus respectivos atributos.

## Recorrido de las páginas
Se genera un bucle `Do - While` que permite recorrer el código según la página en la que esté, con un condicional `if`, sumado al contador de páginas que se actualiza en +1 cada vez que recorra una página.

## Recorrido dentro de la página
Se genera una variable tipo `String` con la dirección de la página y se ingresa a la página a través de `driver.get(variablePag)`.

Posterior al ingreso de la página, se genera un bloque de código `Try - Catch`, para prever errores como una falla en la búsqueda del artículo dentro de la página o que no ocurra el ingreso a la misma.
```java
try {
    // Bloque de código al funcionar
} catch (NoSuchElementException e) {
    System.out.println("No se pudo acceder a la página o encontrar elemento de interés.");
    listaProductos.add(null);
}
```

Dentro del bloque `try`, debe ocurrir la siguiente serie de eventos:
- Búsqueda de los artículos mediante el ingreso de una palabra, a través del buscador de la página. Este buscador deberá identificarse como un elemento web gracias a las etiquetas HTML propias de la página donde se está construyendo la prueba.
```java
WebElement searchBar = driver.findElement(By.id("id-searchbar"));
searchBar.sendKeys("artículo de interés");

WebElement searchClick = driver.findElement(By.className("className-clickSearchIcon"));
searchOne.click();
```

Posterior a la búsqueda del artículo, será necesario identificar el primer producto encontrado por la página. De este producto se tomará la información del nombre. Para esto, se realizará la identificación del elemento web (nuevamente mediante las etiquetas HTML de la página a la que se ingresó) específicamente del nombre, se captura esta información como texto en una variable tipo `String`.
```java
WebElement productName = driver.findElement(By.className("product-name"));
String productTextName = productName.getText().toLowerCase();
```

Identificación del producto correcto, gracias a la variable generada desde el inicio del código. Para esto, se hace una comparación entre el primer producto identificado dentro de las búsquedas de la página y la palabra clave seleccionada. Esto confirma si es el producto correcto y permite proceder a la captura del precio, mediante una condicional.
```java
if (productTextName.contains(keyword.toLowerCase())) {
    // Bloque de código
} else {
    System.out.println("El elemento de búsqueda no coincide con el elemento encontrado");
}
```

Dentro del bloque de código de esta condicional, de coincidir la palabra clave con el nombre del primer artículo, se procede a la captura del precio en una variable tipo `String`, identificando la ubicación del precio mediante las etiquetas HTML de la página.
```java
WebElement productPrice = driver.findElement(By.id("id-price"));
String productTextPrice = productPriceF.getText();
```

Para este punto, ya se tienen los atributos necesarios para guardarlos dentro del `ArrayList`, pero debido a que es necesario realizar una comparación posterior en relación a los precios de tres páginas distintas, para determinar el producto de

 menor valor, se eliminan los caracteres especiales del texto dentro del precio y se procede a transformarlo en números mediante las funciones `replace` y `parseDouble`.
```java
// Eliminación de caracteres especiales.
String textPriceNoSymbol = productTextPrice.replace("$", "");
String textPriceNoPoint = textPriceNoSymbol.replace(".", "");
// Transformación del texto a número.
double price = Double.parseDouble(textPriceNoPoint);
```

Finalmente se crea un nuevo producto y se agregan sus atributos según la información recolectada dentro del código.
```java
listaProductos.add(new Producto(productTextName, price, "Nombre de la Tienda"));
```

En caso de que el producto no coincida con la palabra clave, dentro de la lista de productos se ingresa un valor nulo.
```java
listaProductos.add(null);
```

Esta serie de eventos debe ocurrir en general en cada una de las páginas que se están visitando.

## Preparación de la comparación de precios
Se inicializan tres variables que permitan modificar sus valores a través del recorrido que se realice dentro del ArrayList de los productos agregados:
- Variable tipo `String` vacía, con el nombre del producto de menor valor.
- Variable tipo `String` vacía, con el nombre de la página del producto de menor valor.
- Variable tipo `double` con su valor máximo, para generar la comparación y reemplazarla al recorrer el ArrayList.

## Recorrido del ArrayList
Se genera un bucle `foreach` que permite revisar los productos que se agregaron dentro del ArrayList. Aquí se instala una condicional que permite identificar que el ArrayList no está vacío. Mientras no esté vacío, se genera una variable que se inicia con el valor del precio actual del producto que se está recorriendo en ese instante del bucle.
```java
for (Producto product : listaProductos) {
    if (product != null) {
        double actualPrice = product.getPrice();
```

## Comparador de precios
Se anida dentro del `if` anterior, otro condicional que permite comparar el precio actual con el precio mínimo.
```java
if (actualPrice < minPrice) {
    minPrice = actualPrice;
    minProduct = product.getName();
    productPage = product.getPage();
}
```

Se cierra el condicional que permite identificar que el Array no está vacío, y el bucle `foreach`.

Se imprimen en pantalla los atributos del producto con menor valor (nombre, precio y página donde se encontró).
```java
if (!minProduct.isEmpty()) {
    System.out.println("El producto más económico es: " + minProduct);
    System.out.println("Precio: " + minPrice);
    System.out.println("Puedes encontrarlo en la página: " + productPage);
} else {
    System.out.println("No se encontró ningún producto válido.");
}
```

## Generación de archivo CSV y optimización del proceso
Con el propósito de agilizar la revisión de los elementos a comparar, se implementa la generación de un archivo CSV que posteriormente es interpretado por una sección específica del código. A través de la extracción de la información de las palabras clave y los nombres de los productos, se forman dos ArrayList: uno para los productos y otro para las palabras clave. Estos ArrayList se llenan de acuerdo a la ubicación de las celdas en el archivo. Para realizar esta lectura, se emplea un bloque `try-catch` que incorpora los elementos de las celdas en los respectivos ArrayList de la siguiente manera:
```java
try {
    reader = new CSVReader(new FileReader(productFile));
    String[] cell = reader.readNext();
    while ((cell = reader.readNext()) != null) {
        keywords.add(keyword = cell[0]);
        productNames.add(productName = cell[1]);
}
```

## Integración de variables en el proceso de búsqueda y comparación de precios
Tras completar esta etapa, las variables de nombre de producto y palabra clave son integradas directamente en las áreas designadas para la búsqueda de productos y la comparación de precios. Esto conlleva a una automatización más eficiente de la prueba al encauzar el bucle principal `do-while` dentro de un ciclo `for`. Dicho ciclo recorre ya sea el ArrayList de palabras clave o de productos, según corresponda. Esta optimización contribuye a agilizar el proceso y mejorar la estructura del flujo de trabajo.

```java
for (int i = 0; i < keywords.size(); i++) {
    keyword = keywords.get(i);
    productName = productNames.get(i);
    System.out.println("Search for: [ " + productName + " ]");
    int actualPage = 0;

    // Código principal do - while sobre ingreso a las páginas y captura de información.
}
```
```

## Corrección de Errores

<<<<<<< HEAD
- Se actualiza el Web Driver de Google Chrome para que sea compatible con la versión actual del navegador. Versión final del webdriver 116.0.5845.96 y versión final de Google Chrome 116.0.5845.111
=======
- Se actualiza el Web Driver de Google Chrome para que sea compatible con la versión actual del navegador, versión final del webdriver 116.0.5845.96 y versión final de Google Chrome 116.0.5845.111
>>>>>>> 4ed6ffe0fed4bfe1297938af9b1ab2ba53af525a
