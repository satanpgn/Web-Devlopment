package com.example.pankaj_gadgets.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="products")

public class Product {
    @Id
    @SequenceGenerator(name = "product_seq_gen", sequenceName = "product_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "product_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column( name = "name")
    private String name;
    @Column(name="price")
    private String price;
    @Column(name = "brand")
    private String brand;
    @Column(name = "camera")
    private String camera;
    @Column(name = "color")
    private String color;
    @Column(name = "luncheddate")
    private String luncheddate;
    @Column(name = "storage")
    private String storage;
    @Column(name = "battery")
    private String battery;
    @Column(name = "warranty")
    private String warranty;
    @Column(name = "image")
    private String image;
    @Transient
    private  String imageBase64;



}