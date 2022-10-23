package com.example.springboot.service;

import com.example.springboot.entity.FillQuestion;
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
public interface IFillQuestionService extends IService<FillQuestion> {
    List<Integer> findBySubject(String subject, Integer pageNo);
    int add(FillQuestion fillQuestion);

    FillQuestion findOnlyQuestionId();

    List<FillQuestion> findByIdAndType(Integer paperId);

}
