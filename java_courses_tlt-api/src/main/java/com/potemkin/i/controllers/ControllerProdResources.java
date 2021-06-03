package com.potemkin.i.controllers;

public interface ControllerProdResources {

    public String getProduct(int id);
    
    public String getProducts();
    
    public String addProduct(String strProd);
    
    public String deleteById(int id);
}
