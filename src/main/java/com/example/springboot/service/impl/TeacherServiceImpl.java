package com.example.springboot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springboot.common.Constants;
import com.example.springboot.entity.Student;
import com.example.springboot.entity.StudentDTO;
import com.example.springboot.entity.TeacherDTO;
import com.example.springboot.exception.ServiceException;
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
    private static final Log LOG=Log.get();
    @Override
    public TeacherDTO login(TeacherDTO teacherDTO) {
        Teacher one = getTeacherInfo(teacherDTO);
        if (one != null) {
            BeanUtil.copyProperties(one, teacherDTO, true);
            return teacherDTO;
        } else {
            throw new ServiceException(Constants.CODE_600, "Student name or password error");
        }
    }

    private Teacher getTeacherInfo(TeacherDTO teacherDTO) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", teacherDTO.getID());
        queryWrapper.eq("password", teacherDTO.getPassword());
        Teacher one;
        try {
            one = getOne(queryWrapper); // 从数据库查询用户信息
        } catch (Exception e) {
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500, "System error");
        }
        return one;
    }

}
