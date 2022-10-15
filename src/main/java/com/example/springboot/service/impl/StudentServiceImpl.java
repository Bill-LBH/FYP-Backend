package com.example.springboot.service.impl;

import com.example.springboot.entity.Student;
import com.example.springboot.mapper.StudentMapper;
import com.example.springboot.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 林伯翰
 * @since 2022-10-15
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

}
