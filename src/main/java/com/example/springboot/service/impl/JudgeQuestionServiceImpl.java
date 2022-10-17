package com.example.springboot.service.impl;

import com.example.springboot.entity.JudgeQuestion;
import com.example.springboot.mapper.JudgeQuestionMapper;
import com.example.springboot.service.IJudgeQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 林伯翰
 * @since 2022-10-09
 */
@Service
public class JudgeQuestionServiceImpl extends ServiceImpl<JudgeQuestionMapper, JudgeQuestion> implements IJudgeQuestionService {
    @Resource
    JudgeQuestionMapper judgeQuestionMapper;
    @Override
    public List<Integer> findBySubject(String subject, Integer pageNo) {
        return judgeQuestionMapper.findBySubject(subject,pageNo);
    }

    @Override
    public JudgeQuestion findOnlyQuestionId() {
        return judgeQuestionMapper.findOnlyQuestionId();
    }

    @Override
    public int add(JudgeQuestion judgeQuestion) {
        return judgeQuestionMapper.add(judgeQuestion);
    }

}
