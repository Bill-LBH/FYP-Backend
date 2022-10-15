package com.example.springboot.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.example.springboot.service.IFillQuestionService;
import com.example.springboot.entity.FillQuestion;
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
@RequestMapping("/fill-question")
public class FillQuestionController {
@Autowired
private IFillQuestionService fillQuestionService;

// 新增或者更新
@PostMapping
public boolean save(@RequestBody FillQuestion fillQuestion) {
        return fillQuestionService.saveOrUpdate(fillQuestion);
        }

@DeleteMapping("/{id}")
public Boolean delete(@PathVariable Integer id) {
        return fillQuestionService.removeById(id);
        }

@PostMapping("/del/batch")
public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return fillQuestionService.removeByIds(ids);
        }

@GetMapping
public List<FillQuestion> findAll() {
        return fillQuestionService.list();
        }

@GetMapping("/{id}")
public FillQuestion findOne(@PathVariable Integer id) {
        return fillQuestionService.getById(id);
        }

@GetMapping("/page")
public Page<FillQuestion> findPage(@RequestParam Integer pageNum,
@RequestParam Integer pageSize) {
        QueryWrapper<FillQuestion> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return fillQuestionService.page(new Page<>(pageNum, pageSize), queryWrapper);
        }

        }


