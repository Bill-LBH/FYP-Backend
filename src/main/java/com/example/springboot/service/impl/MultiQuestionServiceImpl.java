package com.example.springboot.service.impl;

import com.example.springboot.entity.MultiQuestion;
import com.example.springboot.mapper.MultiQuestionMapper;
import com.example.springboot.service.IMultiQuestionService;
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
public class MultiQuestionServiceImpl extends ServiceImpl<MultiQuestionMapper, MultiQuestion> implements IMultiQuestionService {

}
