package com.example.pankaj_gadgets.controller;


import com.example.pankaj_gadgets.entity.Comment;
import com.example.pankaj_gadgets.entity.Product;
import com.example.pankaj_gadgets.entity.User;
import com.example.pankaj_gadgets.pojo.CommentPojo;
import com.example.pankaj_gadgets.pojo.Productpojo;
import com.example.pankaj_gadgets.pojo.UserPojo;
import com.example.pankaj_gadgets.services.CommentService;
import com.example.pankaj_gadgets.services.ProductService;
import com.example.pankaj_gadgets.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final ProductService productService;
    private final CommentService commentService;

    @GetMapping("/index")
    public String gethomepage(Model model,Principal principal,
        Authentication authentication) {

            if (authentication!=null){
                Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
                for (GrantedAuthority grantedAuthority : authorities) {
                    if (grantedAuthority.getAuthority().equals("Admin")) {
                        return "redirect:/admin/allproduct";
                    }
                }
            }

            List<Product> products = productService.getEightRandomData();
        model.addAttribute("productfetch", products);
        model.addAttribute("info",userService.findByEmail(principal.getName()));
        return "User/index";
    }


    @GetMapping("/register")
    public String GetRegister(Model model){
        model.addAttribute("user", new UserPojo());
        return "User/login";}

    @PostMapping("/saveregister")
    public String GetRegister(@Valid UserPojo userPojo){
        userService.save(userPojo);

        return "redirect:/user/register";}




    @GetMapping("/AllProduct")
    public String GetMoreItems(Model model) {
        List<Product> productList=productService.findAll();
        model.addAttribute("alllist", productList);
        return "User/home";
    }


//    @GetMapping("/individualgadget")
//    public String GetIndividual(Model model) {
//        return "User/indiProduct";}



    @GetMapping("/productinfo/{id}")
    public String GetmoreInfo(@PathVariable("id") Integer id , Model model, Principal principal){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "redirect:/user/signup";
        }
        Product product= productService.findById(id);
        model.addAttribute("comment", new CommentPojo());
        model.addAttribute("productinfo",new Productpojo(product));
        model.addAttribute("productdata",product);
        model.addAttribute("info",userService.findByEmail(principal.getName()));
        List<Comment> commentList=commentService.findCommentByProductId(id);
        model.addAttribute("allcomments", commentList);
        return "User/indiProduct";}

    @PostMapping("/saveComment")
    public String saveComment(@Valid CommentPojo commentPojo){
        commentService.save(commentPojo);
        return "redirect:/user/index";
    }

    @GetMapping("/myComments/{id}")
    public  String getAppliedJos(@PathVariable("id") Integer id, Model model,Principal principal){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "User/login";
        }
        List<Comment> commentList= commentService.findCommentById(id);
        model.addAttribute("myComments",commentList);

        model.addAttribute("userdata",userService.findByEmail(principal.getName()));

        User user = userService.findBYId(id);
        model.addAttribute("signup", new UserPojo(user));
        model.addAttribute("signups", user);
        return "User/Profile";
    }

    @GetMapping("/deletecomment/{id}")
    public String deleteComment(@PathVariable("id") Integer id) {
        commentService.deleteById(id);
        return "redirect:/user/index";
    }

    @PostMapping("/updateprofile")
    public String updateRegister(@Valid UserPojo userPojo){
        userService.save(userPojo);
        return "redirect:/user/index";}

}
