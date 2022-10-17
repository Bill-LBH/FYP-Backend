package com.example.springboot.service;

import com.example.springboot.entity.MultiQuestion;
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
public interface IMultiQuestionService extends IService<MultiQuestion> {
    List<Integer> findBySubject(String subject, Integer pageNo);

    MultiQuestion findOnlyQuestionId();

    int add(MultiQuestion multiQuestion);

}
