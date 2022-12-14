package com.example.springboot.service;

import com.example.springboot.entity.Exam;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 林伯翰
 * @since 2022-10-09
 */
public interface IExamService extends IService<Exam> {
    Exam Lastexam();
    boolean updatescore(int total, String id);
}
