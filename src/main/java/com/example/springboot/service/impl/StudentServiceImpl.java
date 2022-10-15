package com.example.springboot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springboot.common.Constants;
import com.example.springboot.entity.Student;
import com.example.springboot.entity.StudentDTO;
import com.example.springboot.exception.ServiceException;
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
 * @since 2022-10-09
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {
    private static final Log LOG=Log.get();
    @Override
    public StudentDTO login(StudentDTO studentDTO) {
        Student one = getStudentInfo(studentDTO);
        if (one != null) {
            BeanUtil.copyProperties(one, studentDTO, true);
            return studentDTO;
        } else {
            throw new ServiceException(Constants.CODE_600, "Student name or password error");
        }
    }

    private Student getStudentInfo(StudentDTO studentDTO) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", studentDTO.getId());
        queryWrapper.eq("password", studentDTO.getPassword());
        Student one;
        try {
            one = getOne(queryWrapper); // 从数据库查询用户信息
        } catch (Exception e) {
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500, "System error");
        }
        return one;
    }
}
