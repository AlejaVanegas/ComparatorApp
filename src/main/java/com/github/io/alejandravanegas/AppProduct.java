package com.github.io.alejandravanegas;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class AppProduct {
    public static void main(String[] args) throws CsvValidationException {
        System.setProperty("webdriver.chrome.driver", "C:\\Zenware\\NewDriver\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        ArrayList<Product> productList = new ArrayList<>();
        ArrayList<PageAttributes> attributes = new ArrayList<>();
        ArrayList<Keyword> keywords = new ArrayList<>();

        CSVReader reader = null;

        String productFile = "C:\\Zenware\\Projects\\Selenium\\seleniumlearning\\src\\main\\java\\com\\github\\io\\alejandravanegas\\Producto.txt";

        try {
            reader = new CSVReader(new FileReader(productFile));
            String[] cell = reader.readNext(); // Leera desde la segunda línea.
            while ((cell = reader.readNext()) != null) {
                Keyword keywordList = new Keyword();
                keywordList.setKeywordName(cell[0]);
                keywordList.setProductName(cell[1]);
                keywords.add(keywordList);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        String pages = "C:\\Zenware\\Projects\\Selenium\\seleniumlearning\\src\\main\\java\\com\\github\\io\\alejandravanegas\\Paginas.txt";

        try {
            reader = new CSVReader(new FileReader(pages));
            String[] cell = reader.readNext(); // Leera desde la segunda línea.
            while ((cell = reader.readNext()) != null) {
                PageAttributes attribute = new PageAttributes();
                attribute.setPageName(cell[0]);
                attribute.setPageLink(cell[1]);
                attribute.setPageSearch(cell[2]);
                attribute.setPageProduct(cell[3]);
                attribute.setPagePrice(cell[4]);
                attributes.add(attribute);
            }

            // Variables para el producto actual
            String currentProduct = "";
            double currentPrice = Double.MAX_VALUE;
            String currentPage = "";

            // Iterar a través de los productos
            for (Keyword product : keywords) {
                currentProduct = product.getProductName();
                currentPrice = Double.MAX_VALUE;
                currentPage = "";

                // Iterar a través de las páginas para cada producto
                for (PageAttributes attribute : attributes) {
                    System.out.println("Ingresando a la página: -- " + attribute.getPageName() + " --");
                    System.out.println("Buscando el producto: " + product.getProductName());

                    String page = attribute.getPageLink();
                    driver.get(page);

                    try {
                        WebElement searchPage = driver.findElement(By.id(attribute.getPageSearch()));

                        // Si es la página "K-Tronix", realiza un clic antes de enviar las teclas
                        if (attribute.getPageName().equals("K-Tronix")) {
                            searchPage.sendKeys(product.getProductName());
                            searchPage.click();
                        } else {
                            searchPage.sendKeys(product.getProductName());
                        }

                        searchPage.sendKeys(Keys.ENTER);
                        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

                        // Nombre del producto
                        WebElement productName = null;
                        try {
                            productName = driver.findElement(By.className(attribute.getPageProduct()));
                        } catch (NoSuchElementException e) {
                            // Si no se encuentra por clase, intenta encontrarlo por XPath
                            try {
                                productName = driver.findElement(By.xpath(attribute.getPageProduct()));
                            } catch (NoSuchElementException ex) {
                                // Manejar el caso en que no se puede encontrar el elemento
                                System.out.println("No se pudo encontrar el elemento en la página.");
                            }
                        }

                        if (productName != null) {
                            String productTextName = productName.getText().toLowerCase();
                            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

                            if (productTextName.contains(product.getKeywordName().toLowerCase())) {
                                System.out.println("Nombre del producto: " + productTextName);

                                // Precio del producto
                                WebElement productPrice = null;

                                try {
                                    // Intenta encontrar el precio por XPath
                                    productPrice = driver.findElement(By.xpath(attribute.getPagePrice()));
                                } catch (NoSuchElementException e) {
                                    // Si no se encuentra por XPath, intenta encontrarlo por Selector CSS
                                    try {
                                        productPrice = driver.findElement(By.cssSelector(attribute.getPagePrice()));
                                    } catch (NoSuchElementException ex) {
                                        // Manejar el caso en que no se puede encontrar el elemento del precio
                                        System.out.println("No se pudo encontrar el elemento del precio en la página.");
                                    }
                                }

                                if (productPrice != null) {
                                    if (attribute.getPageName().equals("K-Tronix")) {
                                        String priceText = productPrice.getAttribute("textContent");
                                        System.out.println("Precio del producto: " + priceText);

                                        // Reemplazo del símbolo y punto por espacio en blanco.
                                        String textPriceSymbol = priceText.replace("$", "");
                                        String textPriceNoPoint = textPriceSymbol.replace(".", "");
                                        // Transformación del texto a número.
                                        double price = Double.parseDouble(textPriceNoPoint);
                                        productList.add(new Product(productTextName, price, attribute.getPageName()));

                                        // Actualizar el producto actual si el precio es más bajo
                                        if (price < currentPrice) {
                                            currentPrice = price;
                                            currentPage = attribute.getPageName();
                                        }

                                    } else {
                                        String productTextPrice = productPrice.getText();
                                        System.out.println("Precio del producto: " + productTextPrice);

                                        // Reemplazo del símbolo y punto por espacio en blanco.
                                        String textPriceSymbol = productTextPrice.replace("$", "");
                                        String textPriceNoPoint = textPriceSymbol.replace(".", "");
                                        // Transformación del texto a número.
                                        double price = Double.parseDouble(textPriceNoPoint);

                                        productList.add(new Product(productTextName, price, attribute.getPageName()));
                                        // Actualizar el producto actual si el precio es más bajo
                                        if (price < currentPrice) {
                                            currentPrice = price;
                                            currentPage = attribute.getPageName();
                                        }

                                    }
                                }
                            }

                        } else {
                            System.out.println("No se pudo encontrar el elemento del precio en la página.");
                        }

                    } catch (NoSuchElementException e) {
                        System.out.println("No se pudo encontrar un elemento en la página.");
                    }
                }

                if (!currentProduct.isEmpty()) {
                    System.out.println("-----------------------------------------------------------");
                    System.out.println("El producto más económico de '" + currentProduct + "' es: " + currentProduct);
                    System.out.println("Precio: " + currentPrice);
                    System.out.println("Puedes encontrarlo en la página: " + currentPage);
                    System.out.println("-----------------------------------------------------------");
                }

                for (Product list : productList) {
                    System.out.println("nombre: " + list.getName());
                    System.out.println("precio: " + list.getPrice());
                    System.out.println("pagina:" + list.getPage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }

        String comparator = "C:\\Zenware\\Projects\\Selenium\\seleniumlearning\\src\\main\\java\\com\\github\\io\\alejandravanegas\\Comparaciones.txt";
        File comparatorFile = new File(comparator);

        try {
            boolean fileExists = comparatorFile.exists();

            try (CSVWriter writer = new CSVWriter(new FileWriter(comparator, true))) {
                if (!fileExists) {
                    String[] encabezados = { "pagina1", "producto1", "precio1", "pagina2", "producto2",
                            "precio2",
                            "pagina3", "producto3", "precio3" };
                    writer.writeNext(encabezados);
                }

                int index = 0;
                while (index < productList.size()) {
                    // Agrega una impresión para verificar el inicio del bucle
                    System.out.println("Iniciando la escritura...");

                    Product producto1 = productList.get(index);
                    Product producto2 = (index + 1 < productList.size()) ? productList.get(index + 1) : null;
                    Product producto3 = (index + 2 < productList.size()) ? productList.get(index + 2) : null;

                    String[] fila = {
                            (producto1 != null) ? producto1.getPage() : "",
                            (producto1 != null) ? producto1.getName() : "",
                            (producto1 != null) ? String.valueOf(producto1.getPrice()) : "",
                            (producto2 != null) ? producto2.getPage() : "",
                            (producto2 != null) ? producto2.getName() : "",
                            (producto2 != null) ? String.valueOf(producto2.getPrice()) : "",
                            (producto3 != null) ? producto3.getPage() : "",
                            (producto3 != null) ? producto3.getName() : "",
                            (producto3 != null) ? String.valueOf(producto3.getPrice()) : "",
                    };

                    // Agrega una impresión para verificar si se está escribiendo en el archivo en
                    // cada iteración
                    System.out.println("Escribiendo fila en el archivo: " + Arrays.toString(fila));

                    writer.writeNext(fila);
                    System.out.println("- Producto agregado satisfactoriamente al archivo -");
                    index += 3;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}