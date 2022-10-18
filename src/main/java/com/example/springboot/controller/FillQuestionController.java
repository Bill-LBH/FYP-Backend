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

@Autowired
private PaperManageServiceImpl paperManageService;


// 新增或者更新
@PostMapping
public boolean save(@RequestBody FillQuestion fillQuestion) {
        return fillQuestionService.saveOrUpdate(fillQuestion);
        }

@DeleteMapping("/{Questiontype}/{id}")
public Result delete(@PathVariable Integer Questiontype, @PathVariable Integer id) {
                fillQuestionService.removeById(id);
                Map<String,Object> map=new HashMap<>();
                map.put("questiontype",Questiontype);
                map.put("questionid",id);
                paperManageService.removeByMap(map);
                return Result.success();
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
        queryWrapper.orderByDesc("questionid");
        return fillQuestionService.page(new Page<>(pageNum, pageSize), queryWrapper);
        }

        @PostMapping("/fillQuestion")
        public Result add(@RequestBody FillQuestion fillQuestion) {
                int res = fillQuestionService.add(fillQuestion);
                if (res != 0) {
                        return GlobalExceptionHandler.buildApiResult(Constants.CODE_200,"Add successfully",res);
                }
                return GlobalExceptionHandler.buildApiResult(Constants.CODE_400,"Failed to add",res);
        }

        @GetMapping("/fillQuestionId")
        public Result findOnlyQuestionId() {
                FillQuestion res = fillQuestionService.findOnlyQuestionId();
                return GlobalExceptionHandler.buildApiResult(Constants.CODE_200,"Search successfully",res);
        }

        }


