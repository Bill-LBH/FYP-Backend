package com.example.springboot.service;

import com.example.springboot.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.entity.TeacherDTO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 林伯翰
 * @since 2022-10-09
 */
public interface ITeacherService extends IService<Teacher> {
    TeacherDTO login(TeacherDTO teacherDTO);

}
