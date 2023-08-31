package com.github.io.alejandravanegas;

import java.util.ArrayList;
//import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.*;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;

public class AppProduct {
    public static void main(String[] args) throws CsvValidationException {
        // Scanner read = new Scanner(System.in).useDelimiter("\n");

        // declaration and instantiation of objects/variables

        String productFile = "C:\\Zenware\\Projects\\Selenium\\seleniumlearning\\src\\main\\java\\com\\github\\io\\alejandravanegas\\Producto.txt";
        System.setProperty("webdriver.chrome.driver", "C:\\Zenware\\NewDriver\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        ArrayList<String> keywords = new ArrayList<>();
        ArrayList<String> productNames = new ArrayList<>();

        CSVReader reader = null;
        String keyword = null;
        String productName = null;

        try {
            reader = new CSVReader(new FileReader(productFile));
            String[] cell = reader.readNext(); // Leera desde el segundo.
            while ((cell = reader.readNext()) != null) {
                keywords.add(keyword = cell[0]); // Se modifica acorde al producto
                productNames.add(productName = cell[1]);
                System.out.println("The keyword is: " + keyword);
                System.out.println("The product name is: " + productName);
            }
            // Inicialización del Array List con la clase producto.
            ArrayList<Producto> listaProductos = new ArrayList<>();

            for (int i = 0; i < keywords.size(); i++) {
                keyword = keywords.get(i);
                productName = productNames.get(i);
                System.out.println("Search for: [ " + productName + " ]");
                int actualPage = 0;

                do {
                    actualPage = actualPage + 1;

                    if (actualPage == 1) {
                        System.out.println("Ingresando a la pagina: -- Falabella --");

                        String pageOne = "https://www.falabella.com.co/falabella-co";
                        driver.get(pageOne);
                        try {
                            WebElement searchPageOne = driver.findElement(By.id("testId-SearchBar-Input"));
                            searchPageOne.sendKeys(productName);
                            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                            WebElement searchOne = driver
                                    .findElement(By.className("SearchBar-module_searchBtnIcon__2L2s0"));
                            searchOne.click();

                            // Nombre
                            WebElement productNameF = driver.findElement(By.className("pod-subTitle"));
                            String productTextNameF = productNameF.getText().toLowerCase();
                            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

                            if (productTextNameF.contains(keyword.toLowerCase())) {

                                System.out.println("Nombre del producto: " + productTextNameF);

                                // Precio
                                WebElement productPriceF = driver.findElement(By.xpath("//span[contains(text(),'$')]"));
                                String productTextPriceF = productPriceF.getText();
                                System.out.println("Precio del producto: " + productTextPriceF);

                                // Reemplazo del simbolo y punto por espacio en blanco.
                                String textPriceSymbolF = productTextPriceF.replace("$", "");
                                String textPriceNoPointF = textPriceSymbolF.replace(".", "");
                                // Transformación del texto a número.
                                double priceF = Double.parseDouble(textPriceNoPointF);

                                // Construcción del producto con nombre y precio, agregandolos al Array List
                                listaProductos.add(new Producto(productTextNameF, priceF, "Falabella"));

                            } else {
                                System.out.println("El elemento de búsqueda no coincide con el elemento encontrado");
                                listaProductos.add(null);
                            }

                        } catch (NoSuchElementException e) {
                            System.out.println("No se pudo encontrar un elemento en la página.");
                            listaProductos.add(null);
                        }
                    }

                    if (actualPage == 2) {

                        System.out.println("Ingresando a la pagina: -- Mercado Libre -- ");
                        String pageTwo = "https://www.mercadolibre.com.co/";
                        driver.get(pageTwo);
                        try {
                            WebElement searchPageTwo = driver.findElement(By.id("cb1-edit"));
                            searchPageTwo.sendKeys(productName);
                            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                            WebElement searchTwo = driver.findElement(By.className("nav-icon-search"));
                            searchTwo.click();

                            // Nombre
                            WebElement productNameM = driver
                                    .findElement(By.xpath("//h2[@class='ui-search-item__title shops__item-title']"));
                            String productTextNameM = productNameM.getText().toLowerCase();
                            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

                            if (productTextNameM.contains(keyword.toLowerCase())) {

                                System.out.println("Nombre del producto: " + productTextNameM);

                                // Precio
                                WebElement productPriceM = driver
                                        .findElement(By.xpath("//span[@class='andes-money-amount__fraction']"));
                                String productTextPriceM = productPriceM.getText();
                                System.out.println("Precio del producto: " + productTextPriceM);

                                // Reemplazo del simbolo y punto por espacio en blanco.
                                String textPriceSymbolM = productTextPriceM.replace("$", "");
                                String textPriceNoPointM = textPriceSymbolM.replace(".", "");
                                // Transformación del texto a número.
                                double priceM = Double.parseDouble(textPriceNoPointM);

                                // Construcción del producto con nombre y precio, agregandolos al Array List
                                listaProductos.add(new Producto(productTextNameM, priceM, "Mercado Libre"));

                            } else {
                                System.out.println("El elemento de busqueda no coincide con el elemento encontrado");
                                listaProductos.add(null);
                            }

                        } catch (NoSuchElementException e) {
                            System.out.println("No se pudo encontrar un elemento en la página.");
                            listaProductos.add(null);
                        }
                    }

                    if (actualPage == 3) {
                        System.out.println("Ingresando a la pagina: -- Ktronix -- ");
                        String pageThree = "https://www.ktronix.com/";
                        driver.get(pageThree);
                        try {
                            WebElement searchPageThree = driver.findElement(By.id("js-site-search-input"));
                            searchPageThree.sendKeys(productName);
                            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                            searchPageThree.click();
                            searchPageThree.sendKeys(Keys.ENTER);

                            // Nombre
                            WebElement productNameK = driver
                                    .findElement(By.xpath("//a[contains(@class, 'js-algolia-product-click')]"));
                            String productTextNameK = productNameK.getText().toLowerCase();
                            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

                            if (productTextNameK.contains(keyword.toLowerCase())) {

                                System.out.println("Nombre del Producto: " + productTextNameK);

                                // Precio
                                WebElement productPriceK = driver.findElement(By.cssSelector("span.price"));
                                String priceText = productPriceK.getAttribute("textContent");
                                System.out.println("Precio del Producto: " + priceText);

                                // Transformación del punto por espacio
                                String textPriceSymbolK = priceText.replace("$", "");
                                String textPriceNoPointK = textPriceSymbolK.replace(".", "");
                                // Transformación del texto a número.
                                double priceK = Double.parseDouble(textPriceNoPointK);

                                // Construcción del producto con nombre y precio, agregandolos al Array List
                                listaProductos.add(new Producto(productTextNameK, priceK, "K - Tronix"));

                            } else {
                                System.out.println("El elemento de busqueda no coincide con el elemento encontrado");
                                listaProductos.add(null);
                            }

                        } catch (NoSuchElementException e) {
                            System.out.println("No se pudo encontrar un elemento en la página.");
                            listaProductos.add(null);
                        }
                    }

                } while (actualPage <= 3);

                // Comparador de precios:

                String minProduct = "";
                String productPage = "";
                double minPrice = Double.MAX_VALUE;

                for (Producto product : listaProductos) {
                    if (product != null) {
                        double actualPrice = product.getPrice();
                        if (actualPrice < minPrice) {
                            minPrice = actualPrice;
                            minProduct = product.getName();
                            productPage = product.getPage();
                        }
                    }
                }

                if (!minProduct.isEmpty()) {
                    System.out.println("El producto más económico es: " + minProduct);
                    System.out.println("Precio: " + minPrice);
                    System.out.println("Puedes encontrarlo en la pagina: " + productPage);
                } else {
                    System.out.println("No se encontró ningún producto válido.");
                }

                
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.close();
        
    }
}
