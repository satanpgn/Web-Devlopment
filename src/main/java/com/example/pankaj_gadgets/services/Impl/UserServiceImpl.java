package com.example.pankaj_gadgets.services.Impl;


import com.example.pankaj_gadgets.Exception.AppException;
import com.example.pankaj_gadgets.config.PasswordEncoderUtil;
import com.example.pankaj_gadgets.entity.User;
import com.example.pankaj_gadgets.pojo.UserPojo;
import com.example.pankaj_gadgets.repo.UserRepo;
import com.example.pankaj_gadgets.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private  final UserRepo userRepo;

    @Override
    public UserPojo save(UserPojo userPojo){
        User user;
        if (userPojo.getId() != null) {
            user = userRepo.findById(userPojo.getId()).orElseThrow(() -> new RuntimeException("Not Found"));
        } else {
            user = new User();
        }
        user.setEmail(userPojo.getEmail());
        user.setFullname(userPojo.getFullname());
//        user.setPhoneNo(userPojo.getMobile_no());
        user.setPassword(PasswordEncoderUtil.getInstance().encode(userPojo.getPassword()));

//        if(userPojo.getImage()!=null){
//            StringBuilder fileNames = new StringBuilder();
//            System.out.println(UPLOAD_DIRECTORY);
//            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, userPojo.getImage().getOriginalFilename());
//            fileNames.append(userPojo.getImage().getOriginalFilename());
//            Files.write(fileNameAndPath, userPojo.getImage().getBytes());
//            user.setImage(userPojo.getImage().getOriginalFilename());
//        }


        userRepo.save(user);
        return new UserPojo(user);


    }

    @Override
    public User findByEmail(String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new AppException("Invalid User email", HttpStatus.BAD_REQUEST));
        return user;
    }

    @Override
    public User findBYId(Integer id) {
        User user= userRepo.findById(id).orElseThrow(()->new RuntimeException("not found"));
        user=User.builder()
                .id(user.getId())
                .fullname(user.getFullname())
                .email(user.getEmail())
//                .password(user.getPassword())
                .build();
        return user;
    }
}