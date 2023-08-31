package com.github.io.alejandravanegas;

public class Producto {

    private String name; 
    private double price;
    private String page;

    public Producto(String name, double price, String page) {
        this.name = name;
        this.price = price;
        this.page = page; 
    }


    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getPage() {
        return page;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }

    public void setPage(String page) {
        this.page = page; 
    }

}
