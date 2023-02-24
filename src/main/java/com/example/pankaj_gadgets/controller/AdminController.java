package com.example.pankaj_gadgets.controller;

import com.example.pankaj_gadgets.entity.Comment;
import com.example.pankaj_gadgets.entity.Product;
import com.example.pankaj_gadgets.pojo.Productpojo;
import com.example.pankaj_gadgets.services.CommentService;
import com.example.pankaj_gadgets.services.ProductService;
import com.example.pankaj_gadgets.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final ProductService productService;
    private final CommentService commentService;

    @GetMapping("/dashboard")
    public String gethomepage(Model model) {
        return "Admin/admin";
    }


    @GetMapping("/addproduct")
    public String getAddProduct(Model model) {
        model.addAttribute("add",new Productpojo());
        return "Admin/add_product";
    }
    @PostMapping("/saveproduct")
    public String createUser(@Valid Productpojo productPojo,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes
    ) throws Exception {

        Map<String, String> requestError = validateRequest(bindingResult);
        if (requestError != null) {
            redirectAttributes.addFlashAttribute("requestError", requestError);
            return "redirect:/admin/addproduct";
        }
        productService.save(productPojo);
        redirectAttributes.addFlashAttribute("successMsg", "User saved successfully");
        return "redirect:/admin/allproduct";


    }

    @GetMapping("/allproduct")
    public String getAllProduct(Model model) {
        List<Product> productList=productService.findAll();
        model.addAttribute("alllist", productList);
        return "Admin/AllProduct";
    }

    @GetMapping("/editproduct/{id}")
    public String editMembers(@PathVariable("id") Integer id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("add", new Productpojo(product));
        return "Admin/add_product";
    }

    @GetMapping("/deleteproduct/{id}")
    public String deleteMembers(@PathVariable("id") Integer id) {
        productService.deleteById(id);
        return "redirect:/admin/allproduct";
    }


    @GetMapping("/allcomments")
    public String getAllComments(Model model) {
        List<Comment> commentList=commentService.findAll();
        model.addAttribute("allcomments", commentList);
        return "Admin/AllComments";
    }


    @GetMapping("/deletecomment/{id}")
    public String deleteComment(@PathVariable("id") Integer id) {
        commentService.deleteById(id);
        return "redirect:/admin/allcomments";
    }



    public Map<String, String> validateRequest(BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            return null;
        }
        Map<String, String> errors = new HashMap<>();
        bindingResult.getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return errors;

    }

}
