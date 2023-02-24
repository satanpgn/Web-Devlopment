package com.example.pankaj_gadgets.services;

import com.example.pankaj_gadgets.entity.Product;
import com.example.pankaj_gadgets.pojo.Productpojo;
import com.example.pankaj_gadgets.repo.ProductRepo;

import java.util.*;

public interface ProductService {
    Productpojo save(Productpojo productpojo) throws  Exception;
    List<Product> findAll();

    Product findById(Integer id);

    void deleteById(Integer id);
    List<Product> getEightRandomData();


}
