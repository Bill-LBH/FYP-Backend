package com.example.springboot.service.impl;

import com.example.springboot.entity.FillQuestion;
import com.example.springboot.mapper.FillQuestionMapper;
import com.example.springboot.service.IFillQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class FillQuestionServiceImpl extends ServiceImpl<FillQuestionMapper, FillQuestion> implements IFillQuestionService {
    @Resource
    FillQuestionMapper fillQuestionMapper;
    @Override
    public List<Integer> findBySubject(String subject, Integer pageNo) {
        return fillQuestionMapper.findBySubject(subject,pageNo);
    }

    @Override
    public int add(FillQuestion fillQuestion) {
        return fillQuestionMapper.add(fillQuestion);
    }

    @Override
    public FillQuestion findOnlyQuestionId() {
        return fillQuestionMapper.findOnlyQuestionId();
    }

    @Override
    public List<FillQuestion> findByIdAndType(Integer paperId) {
        return fillQuestionMapper.findByIdAndType(paperId);
    }

}
