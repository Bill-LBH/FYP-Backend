package com.example.springboot;

import com.example.springboot.mapper.UserMapper;
import com.example.springboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@RestController
@SpringBootApplication
@EnableWebMvc
public class SpringbootApplication {



    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }


}
