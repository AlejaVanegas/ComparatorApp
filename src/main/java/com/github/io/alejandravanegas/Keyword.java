package com.github.io.alejandravanegas;

public class Keyword {
    private String keywordName;
    private String productName;
    
    public Keyword() {
    }
    
    public Keyword(String keywordName, String productName) {
        this.keywordName = keywordName;
        this.productName = productName;
    }
    
    public String getKeywordName() {
        return keywordName;
    }
    
    public void setKeywordName(String keywordName) {
        this.keywordName = keywordName;
    }
   
    public String getProductName() {
        return productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    } 
}
