package com.example.ProductService.Service.Impl;


import com.example.ProductService.Dto.ProductDto;
import com.example.ProductService.Entitiy.Product;
import com.example.ProductService.Repository.ProductRepository;
import com.example.ProductService.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceDatabaseImpl implements ProductService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDto> getAllProducts(String Product_URL) {

        List<ProductDto> products = new ArrayList<>();

        List<Product> productsFromDatabase = productRepository.findAll();

        if (!productsFromDatabase.isEmpty()) {
            for (Product p : productsFromDatabase) {
                ProductDto product = new ProductDto();
                product.setId(p.getId());
                product.setTitle(p.getTitle());
                product.setPrice(p.getPrice());
                product.setDescription(p.getDescription());
                product.setCategory(p.getCategory());
                product.setImage(p.getImage());
                products.add(product);
            }
        }
        return products;
    }

    public void saveProduct(String Product_URL,Long id){
        ProductDto product = restTemplate.getForObject(Product_URL+"/"+id, ProductDto.class);
        System.out.println("This is Product from APi : "+product.toString());
        Product p = new Product();
        p.setId((Long) id);
        p.setTitle(product.getTitle());
        p.setPrice(product.getPrice());
        p.setDescription(product.getDescription());
        p.setCategory(product.getCategory());
        p.setImage(product.getImage());
    try {
        productRepository.save(p);
        System.out.println("Product saved successfully");
    }catch (Exception e){
        System.out.println("Product not saved. The Exception error is : "+e.getMessage());
    }

    }





    public ProductDto getProductById(String Product_URL, Long id) {

        try {
            Optional<Product> productOptional = productRepository.findById(id);
            if (productOptional.isPresent()) {
                Product product = productOptional.get();
                ProductDto productDto = new ProductDto();
                productDto.setId(product.getId());
                productDto.setTitle(product.getTitle());
                productDto.setDescription(product.getDescription());
                productDto.setCategory(product.getCategory());
                productDto.setImage(product.getImage());
                productDto.setPrice(product.getPrice());
                return productDto;
            }
        }
        catch (Exception e){
            System.out.println("Product not found. The Exception error is : "+e.getMessage());
        }
        return new ProductDto();
    }


}
