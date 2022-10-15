package com.example.springboot.config.service.impl;

import com.example.springboot.entity.JudgeQuestion;
import com.example.springboot.mapper.JudgeQuestionMapper;
import com.example.springboot.config.service.IJudgeQuestionService;
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
public class JudgeQuestionServiceImpl extends ServiceImpl<JudgeQuestionMapper, JudgeQuestion> implements IJudgeQuestionService {

}
