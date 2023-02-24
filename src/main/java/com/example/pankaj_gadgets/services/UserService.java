package com.example.pankaj_gadgets.services;

import com.example.pankaj_gadgets.entity.User;
import com.example.pankaj_gadgets.pojo.UserPojo;

public interface UserService {
    UserPojo save(UserPojo userPojo) ;

    User findByEmail(String email);
    User findBYId(Integer id);
}
