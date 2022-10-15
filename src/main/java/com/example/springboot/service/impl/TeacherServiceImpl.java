package com.example.springboot.service.impl;

import com.example.springboot.service.ITeacherService;
import com.example.springboot.entity.Teacher;
import com.example.springboot.mapper.TeacherMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 林伯翰
 * @since 2022-10-09
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {

}
