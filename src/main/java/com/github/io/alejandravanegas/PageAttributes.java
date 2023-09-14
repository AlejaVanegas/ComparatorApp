package com.github.io.alejandravanegas;

public class PageAttributes {
    private String pageName;
    private String pageLink;
    private String pageSearch;
    private String pageProduct;
    private String pagePrice;
    
    public PageAttributes() {
    }
    
    public PageAttributes(String pageName, String pageLink, String pageSearch, String pageProduct, String pagePrice) {
        this.pageName = pageName;
        this.pageLink = pageLink;
        this.pageSearch = pageSearch;
        this.pageProduct = pageProduct;
        this.pagePrice = pagePrice;
    }
    
    public String getPageName() {
        return pageName;
    }
    
    public void setPageName(String pageName) {
        this.pageName = pageName;
    }
    
    public String getPageLink() {
        return pageLink;
    }
    
    public void setPageLink(String pageLink) {
        this.pageLink = pageLink;
    }
    
    public String getPageSearch() {
        return pageSearch;
    }
    
    public void setPageSearch(String pageSearch) {
        this.pageSearch = pageSearch;
    }
    
    public String getPageProduct() {
        return pageProduct;
    }
    
    public void setPageProduct(String pageProduct) {
        this.pageProduct = pageProduct;
    }
    
    public String getPagePrice() {
        return pagePrice;
    }
    
    public void setPagePrice(String pagePrice) {
        this.pagePrice = pagePrice;
    } 
}
