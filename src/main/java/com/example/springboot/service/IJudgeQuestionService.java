package com.example.springboot.service;

import com.example.springboot.entity.JudgeQuestion;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 林伯翰
 * @since 2022-10-09
 */
public interface IJudgeQuestionService extends IService<JudgeQuestion> {
    List<Integer> findBySubject(String subject, Integer pageNo);
    JudgeQuestion findOnlyQuestionId();

    int add(JudgeQuestion judgeQuestion);

    List<JudgeQuestion> findByIdAndType(Integer paperId);
}
