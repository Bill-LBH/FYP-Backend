package com.example.springboot.config.service.impl;

import com.example.springboot.entity.Score;
import com.example.springboot.mapper.ScoreMapper;
import com.example.springboot.config.service.IScoreService;
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
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements IScoreService {

}
