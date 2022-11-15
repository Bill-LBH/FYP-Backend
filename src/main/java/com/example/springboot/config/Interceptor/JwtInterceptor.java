package com.example.springboot.config.Interceptor;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.springboot.common.Constants;
import com.example.springboot.entity.Student;
import com.example.springboot.entity.Teacher;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.service.IStudentService;
import com.example.springboot.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private IStudentService studentService;
    @Autowired
    private ITeacherService teacherService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token");
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        if (StrUtil.isBlank(token)) {
            throw new ServiceException(Constants.CODE_401, "No token, please log in again");
        }
        // 获取 token 中的 user id
        String Id;
        try {
            Id = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new ServiceException(Constants.CODE_401, "token verification failed, please log in again");
        }
        Student student = studentService.getById(Id);
        Teacher teacher= teacherService.getById(Id);
        if (student == null) {
            if(teacher==null){
                throw new ServiceException(Constants.CODE_401, "User does not exist, please log in again");
            }
        }

        if(student!=null){
            // 用户密码加签验证 token
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(student.getPassword())).build();
            try {
                jwtVerifier.verify(token); // 验证token
            } catch (JWTVerificationException e) {
                throw new ServiceException(Constants.CODE_401, "token verification failed, please log in again");
            }
        }
        if (teacher !=null){
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(teacher.getPassword())).build();
            try {
                jwtVerifier.verify(token); // 验证token
            } catch (JWTVerificationException e) {
                throw new ServiceException(Constants.CODE_401, "token verification failed, please log in again");
            }
        }

        return true;

    }
}
