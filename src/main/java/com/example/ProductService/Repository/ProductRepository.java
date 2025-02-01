package com.example.ProductService.Repository;

import com.example.ProductService.Dto.ProductDto;
import com.example.ProductService.Entitiy.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository< Product,Long> {

    Optional<Product> findById(long id);


    Product save(Product product);

    List<Product> findAll();

}