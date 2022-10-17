package com.example.springboot.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Constants;
import com.example.springboot.common.Result;
import com.example.springboot.exception.GlobalExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        @PostMapping
        public Result add(@RequestBody JudgeQuestion judgeQuestion) {
                int res = judgeQuestionService.add(judgeQuestion);
                if (res != 0) {
                        return GlobalExceptionHandler.buildApiResult(Constants.CODE_200,"Add successfully",res);
                }
                return GlobalExceptionHandler.buildApiResult(Constants.CODE_400,"Failed to add",res);
        }

        @GetMapping("/judgeQuestionId")
        public Result findOnlyQuestionId() {
                JudgeQuestion res = judgeQuestionService.findOnlyQuestionId();
                return  GlobalExceptionHandler.buildApiResult(Constants.CODE_200,"Search successfully",res);
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
        queryWrapper.orderByDesc("questionid");
        return judgeQuestionService.page(new Page<>(pageNum, pageSize), queryWrapper);
        }

        }


