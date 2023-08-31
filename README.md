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
- Web Driver: Chrome Versión 114.0.5735.90.

## Paso a Paso del Código

1. **Construcción de la carpeta del proyecto:** Se crea una carpeta específica para el proyecto y se configura el IDE para la generación de archivos base.

2. **Nuevo proyecto:** Se utiliza el IDE Visual Code Studio y se crea un nuevo proyecto con el arquetipo "Maven archetype quickstart" 1.4.

3. **Modificación del archivo pom.xml:** Se incluyen las dependencias necesarias para la herramienta de automatización Selenium.

```xml
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>3.11.0</version>
</dependency>
```

4. **Inicialización del Webdriver:** Se descarga el Web Driver de Google Chrome y se inicia en el código.

```java
System.setProperty("webdriver.chrome.driver", "dirección\\de\\la\\carpeta");
WebDriver driver = new ChromeDriver();
```

5. **Construcción de una clase Producto:** Se crea una clase "Producto" con atributos como Nombre, Precio y Página.

6. **Asignación de variables e inicialización de colecciones:** Se utilizan variables para contar páginas, definir palabras clave y almacenar productos.

7. **Recorrido de las páginas:** Se recorren las páginas web y se busca el artículo de interés en cada una.

8. **Recorrido dentro de la página:** Se busca y captura la información del producto dentro de la página.

9. **Preparación de la comparación de precios:** Se inicializan variables para la comparación de precios.

10. **Recorrido del ArrayList:** Se compara el precio de cada producto y se encuentra el más económico.

11. **Comparador de precios:** Se identifica el producto con el precio más bajo y se imprime en pantalla.

## Corrección de Errores

- Se actualiza el Web Driver de Google Chrome para que sea compatible con la versión actual del navegador, versión final del webdriver 116.0.5845.96 y versión final de Google Chrome 116.0.5845.111
