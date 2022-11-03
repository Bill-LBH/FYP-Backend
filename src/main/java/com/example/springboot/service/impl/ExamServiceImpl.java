package com.example.springboot.service.impl;

import com.example.springboot.entity.Exam;
import com.example.springboot.mapper.ExamMapper;
import com.example.springboot.service.IExamService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 林伯翰
 * @since 2022-10-09
 */
@Service
public class ExamServiceImpl extends ServiceImpl<ExamMapper, Exam> implements IExamService {
    @Resource
    ExamMapper examMapper;
    @Override
    public Exam Lastexam(){
        return examMapper.LastExam();
    }

    @Override
    public boolean updatescore(int total,String id){
        return examMapper.updatescore(total,id);
    }

}
