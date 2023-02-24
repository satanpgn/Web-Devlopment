package com.example.pankaj_gadgets.services.Impl;

import com.example.pankaj_gadgets.entity.Product;
import com.example.pankaj_gadgets.pojo.Productpojo;
import com.example.pankaj_gadgets.repo.ProductRepo;
import com.example.pankaj_gadgets.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private  final ProductRepo productrepo;

    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/product";

    @Override
    public Productpojo save(Productpojo productpojo) throws Exception {
        Product user;
        if (productpojo.getId() != null) {
            user = productrepo.findById(productpojo.getId()).orElseThrow(() -> new RuntimeException("Not Found"));
        } else {
            user = new Product();
        }
        if(productpojo.getId()!=null){
            user.setId(user.getId());
        }
        user.setBattery(productpojo.getBattery());
        user.setBrand(productpojo.getBrand());
        user.setCamera(productpojo.getCamera());
        user.setColor(productpojo.getColor());
        user.setPrice(productpojo.getPrice());
        user.setStorage(productpojo.getStorage());
        user.setWarranty(productpojo.getWarranty());
        user.setLuncheddate(productpojo.getLuncheddate());
        user.setName(productpojo.getName());

        if(productpojo.getImage()!=null){
            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, productpojo.getImage().getOriginalFilename());
            fileNames.append(productpojo.getImage().getOriginalFilename());
            Files.write(fileNameAndPath, productpojo.getImage().getBytes());
            user.setImage(productpojo.getImage().getOriginalFilename());
        }


        productrepo.save(user);
        return new Productpojo(user);

    }

    @Override
    public List<Product> findAll() {
        return findAllinList(productrepo.findAll());
    }

    @Override
    public Product findById(Integer id) {
        Product product=productrepo.findById(id).orElseThrow(()-> new RuntimeException("not found"));
        product=Product.builder()
                .id(product.getId())
                .battery(product.getBattery())
                .camera(product.getCamera())
                .price(product.getPrice())
                .color(product.getColor())
                .luncheddate(product.getLuncheddate())
                .warranty(product.getWarranty())
                .storage(product.getStorage())
                .name(product.getName())
                .brand(product.getBrand())
                .imageBase64(getImageBase64(product.getImage()))
                .build();
        return product;
    }

    @Override
    public void deleteById(Integer id) {
        productrepo.deleteById(id);
    }

    @Override
    public List<Product> getEightRandomData() {
        return findAllinList(productrepo.getEightRandomData());
    }



    public List<Product> findAllinList(List<Product> list){

        Stream<Product> allJobsWithImage = list.stream().map(product ->
                Product.builder()
                        .id(product.getId())
                        .battery(product.getBattery())
                        .camera(product.getCamera())
                        .price(product.getPrice())
                        .color(product.getColor())
                        .luncheddate(product.getLuncheddate())
                        .warranty(product.getWarranty())
                        .storage(product.getStorage())
                        .name(product.getName())
                        .brand(product.getBrand())
                        .imageBase64(getImageBase64(product.getImage()))
                        .build()
        );
        list = allJobsWithImage.toList();
        return list;
    }


    public String getImageBase64(String fileName) {
        if (fileName!=null) {
            String filePath = System.getProperty("user.dir")+ "/product/";
            File file = new File(filePath + fileName);
            byte[] bytes;
            try {
                bytes = Files.readAllBytes(file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return Base64.getEncoder().encodeToString(bytes);
        }
        return null;
    }
}
