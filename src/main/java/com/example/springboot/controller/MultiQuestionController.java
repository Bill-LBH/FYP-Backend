package com.example.springboot.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.example.springboot.service.IMultiQuestionService;
import com.example.springboot.entity.MultiQuestion;
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
@RequestMapping("/multi-question")
public class MultiQuestionController {
@Autowired
private IMultiQuestionService multiQuestionService;

// 新增或者更新
@PostMapping
public boolean save(@RequestBody MultiQuestion multiQuestion) {
        return multiQuestionService.saveOrUpdate(multiQuestion);
        }

@DeleteMapping("/{id}")
public Boolean delete(@PathVariable Integer id) {
        return multiQuestionService.removeById(id);
        }

@PostMapping("/del/batch")
public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return multiQuestionService.removeByIds(ids);
        }

@GetMapping
public List<MultiQuestion> findAll() {
        return multiQuestionService.list();
        }

@GetMapping("/{id}")
public MultiQuestion findOne(@PathVariable Integer id) {
        return multiQuestionService.getById(id);
        }

@GetMapping("/page")
public Page<MultiQuestion> findPage(@RequestParam Integer pageNum,
@RequestParam Integer pageSize) {
        QueryWrapper<MultiQuestion> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("questionid");
        return multiQuestionService.page(new Page<>(pageNum, pageSize), queryWrapper);
        }

        }


