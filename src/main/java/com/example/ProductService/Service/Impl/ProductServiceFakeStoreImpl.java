package com.example.ProductService.Service.Impl;

import com.example.ProductService.Dto.ProductDto;
import com.example.ProductService.Entitiy.Product;
import com.example.ProductService.Repository.ProductRepository;
import com.example.ProductService.Service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.DataInput;
import java.util.List;

@Service
@Primary
public class ProductServiceFakeStoreImpl implements ProductService {



    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public ProductDto getProductById(String url, Long id) {

        ProductDto productFromCache = (ProductDto) redisTemplate.opsForHash().get("PRODUCTS","PRODUCT_"+id);
            if(productFromCache!=null){
                System.out.println("This is Product from Cache : "+productFromCache.toString());
                return productFromCache;
            }

        ProductDto product = restTemplate.getForObject(url+"/"+id, ProductDto.class);

        System.out.println("This is Product from APi : "+product.toString());

        try {
            System.out.println("Product saved successfully");
            redisTemplate.opsForHash().put("PRODUCTS","PRODUCT_"+id,product);
            System.out.println("Product saved to cache successfully");
        }
        catch (Exception e){
            System.out.println("Product not saved. The Exception error is : "+e.getMessage());
        }

        return product;
    }


    @Override
    public void saveProduct(String url, Long id) {
        String response = restTemplate.getForObject(url+"/"+id, String.class);
        System.out.println("This is Product from APi : "+response);

        try {
            // Manually convert the response to Product using ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();
            Product product = objectMapper.readValue( response, Product.class);

            productRepository.save(product);
            System.out.println("Product saved successfully");
        } catch (Exception e) {
            System.out.println("Product not saved. The Exception error is: " + e.getMessage());
        }


    }


    public void saveAllProductsToDB(String url){

    }



    @Override
    public List<ProductDto> getAllProducts(String url) {

        ResponseEntity<List<ProductDto>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ProductDto>>() {}
        );

        System.out.println("This is just response  : "+response.getBody()  );

        return response.getBody();
    }
}
