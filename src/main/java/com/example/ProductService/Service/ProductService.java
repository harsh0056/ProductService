package com.example.ProductService.Service;

import com.example.ProductService.Dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto getProductById(String url, Long id);

    void saveProduct(String url,Long id);

    List<ProductDto> getAllProducts(String url);

}
