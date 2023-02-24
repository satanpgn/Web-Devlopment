package com.example.pankaj_gadgets.repo;

import com.example.pankaj_gadgets.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {

    @Query(value = "SELECT * FROM products ORDER BY RANDOM() LIMIT 8", nativeQuery = true)
    List<Product> getEightRandomData();
}
