package com.example.springboot.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.example.springboot.service.IJudgeQuestionService;
import com.example.springboot.entity.JudgeQuestion;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 林伯翰
 * @since 2022-10-15
 */
@RestController
@RequestMapping("/judge-question")
public class JudgeQuestionController {
@Autowired
private IJudgeQuestionService judgeQuestionService;

// 新增或者更新
@PostMapping
public boolean save(@RequestBody JudgeQuestion judgeQuestion) {
        return judgeQuestionService.saveOrUpdate(judgeQuestion);
        }

@DeleteMapping("/{id}")
public Boolean delete(@PathVariable Integer id) {
        return judgeQuestionService.removeById(id);
        }

@PostMapping("/del/batch")
public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return judgeQuestionService.removeByIds(ids);
        }

@GetMapping
public List<JudgeQuestion> findAll() {
        return judgeQuestionService.list();
        }

@GetMapping("/{id}")
public JudgeQuestion findOne(@PathVariable Integer id) {
        return judgeQuestionService.getById(id);
        }

@GetMapping("/page")
public Page<JudgeQuestion> findPage(@RequestParam Integer pageNum,
@RequestParam Integer pageSize) {
        QueryWrapper<JudgeQuestion> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return judgeQuestionService.page(new Page<>(pageNum, pageSize), queryWrapper);
        }

        }


