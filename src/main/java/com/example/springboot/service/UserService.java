package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper,User> {
public boolean SaveUser(User user){
    return saveOrUpdate(user);
}
}
