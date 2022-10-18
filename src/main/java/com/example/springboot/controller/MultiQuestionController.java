package com.example.springboot.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Constants;
import com.example.springboot.common.Result;
import com.example.springboot.exception.GlobalExceptionHandler;
import com.example.springboot.service.impl.PaperManageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        @Autowired
        private PaperManageServiceImpl paperManageService;

// 新增或者更新
@PostMapping
public boolean save(@RequestBody MultiQuestion multiQuestion) {
        return multiQuestionService.saveOrUpdate(multiQuestion);
        }

        @GetMapping("/multiQuestionId")
        public Result findOnlyQuestion() {
                MultiQuestion res = multiQuestionService.findOnlyQuestionId();
                return GlobalExceptionHandler.buildApiResult(Constants.CODE_200,"Search successfully",res);
        }

        @DeleteMapping("/{Questiontype}/{id}")
        public Result delete(@PathVariable Integer Questiontype, @PathVariable Integer id) {
                multiQuestionService.removeById(id);
                Map<String,Object> map=new HashMap<>();
                map.put("questiontype",Questiontype);
                map.put("questionid",id);
                paperManageService.removeByMap(map);
                return Result.success();
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

        @PostMapping("/MultiQuestion")
        public Result add(@RequestBody MultiQuestion multiQuestion) {
                int res = multiQuestionService.add(multiQuestion);
                if (res != 0) {

                        return GlobalExceptionHandler.buildApiResult(Constants.CODE_200,"Add successfully",res);
                }
                return GlobalExceptionHandler.buildApiResult(Constants.CODE_400,"Add failed",res);
        }

@GetMapping("/page")
public Page<MultiQuestion> findPage(@RequestParam Integer pageNum,
@RequestParam Integer pageSize) {
        QueryWrapper<MultiQuestion> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("questionid");
        return multiQuestionService.page(new Page<>(pageNum, pageSize), queryWrapper);
        }

        }


