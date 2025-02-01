package com.example.ProductService.Controller;


import com.example.ProductService.Dto.ProductDto;
import com.example.ProductService.Entitiy.Product;
import com.example.ProductService.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class productController {

    private final static String Product_URL = "http://fakestoreapi.com/products";

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<ProductDto> getAllProducts(){
        return productService.getAllProducts(Product_URL);
    }

    @GetMapping("/products/{id}")
    public ProductDto getProductById(@PathVariable("id") Long id){
        return productService.getProductById(Product_URL,id);
    }

    @GetMapping("/products/save/{id}")
    public void saveProduct(@PathVariable("id") Long id){
        productService.saveProduct(Product_URL,id);
    }

}
