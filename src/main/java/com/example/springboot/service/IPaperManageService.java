package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.entity.PaperManage;

public interface IPaperManageService extends IService<PaperManage> {
    int add(PaperManage paperManage);
}
