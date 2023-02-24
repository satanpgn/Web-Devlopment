package com.example.pankaj_gadgets.pojo;

import com.example.pankaj_gadgets.entity.Product;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Productpojo {
    private  Integer id;
    private  String name;
    private  String price;
    private  String brand;
    private  String camera;
    private  String color;
    private  String storage;
    private  String luncheddate;
    private  String battery;
    private  String warranty;
    private MultipartFile image;
    public Productpojo(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.brand = product.getBrand();
        this.camera = product.getCamera();
        this.color = product.getColor();
        this.storage = product.getStorage();
        this.luncheddate = product.getLuncheddate();
        this.battery = product.getBattery();
        this.warranty = product.getWarranty();
    }

}
