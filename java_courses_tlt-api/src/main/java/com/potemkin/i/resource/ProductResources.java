package com.potemkin.i.resource;

public interface ProductResources {

    public String getProduct(int id);
    
    public String getProducts();
    
    public String addProduct(String strProd);
    
    public String deleteById(int id);
}
