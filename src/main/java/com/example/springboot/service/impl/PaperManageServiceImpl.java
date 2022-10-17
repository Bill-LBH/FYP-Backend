package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.service.IPaperManageService;
import com.example.springboot.entity.PaperManage;
import com.example.springboot.mapper.PaperManageMapper;
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
public class PaperManageServiceImpl extends ServiceImpl<PaperManageMapper, PaperManage> implements IPaperManageService {
    @Resource
    private PaperManageMapper paperMapper;
    @Override
    public int add(PaperManage paperManage) {
        return paperMapper.add(paperManage);
    }

}