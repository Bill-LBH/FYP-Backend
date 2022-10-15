package com.example.springboot.config.service;

import com.example.springboot.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.entity.StudentDTO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 林伯翰
 * @since 2022-10-09
 */
public interface IStudentService extends IService<Student> {

     StudentDTO login(StudentDTO studentDTO);
}
