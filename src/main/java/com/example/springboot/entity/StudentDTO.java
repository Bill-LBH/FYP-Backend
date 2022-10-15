package com.example.springboot.entity;

import lombok.Data;

@Data
public class StudentDTO {
    private String id;
    private String password;
    private String avatar;
    private String nickname;
}
