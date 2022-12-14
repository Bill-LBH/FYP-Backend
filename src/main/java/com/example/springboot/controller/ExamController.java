package com.example.springboot.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Constants;
import com.example.springboot.common.Result;
import com.example.springboot.entity.ScoreDTO;
import com.example.springboot.exception.GlobalExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.example.springboot.service.IExamService;
import com.example.springboot.entity.Exam;
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
@RequestMapping("/exam")
public class ExamController {
@Autowired
private IExamService examService;

// 新增或者更新
@PostMapping
public boolean save(@RequestBody Exam exam) {
        return examService.saveOrUpdate(exam);
}


@PostMapping("/score")
public Result update(@RequestBody ScoreDTO scoreDTO){
        boolean res=examService.updatescore(scoreDTO.getScore(),scoreDTO.getId());
        if(!res) {
                return GlobalExceptionHandler.buildApiResult(Constants.CODE_400,"Update failed",null);
        }
        else {
                return GlobalExceptionHandler.buildApiResult(Constants.CODE_200, "Update successfully！", res);
        }

}

@DeleteMapping("/{id}")
public Boolean delete(@PathVariable Integer id) {
        return examService.removeById(id);
        }

@PostMapping("/del/batch")
public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return examService.removeByIds(ids);
        }

@GetMapping
public List<Exam> findAll() {
        return examService.list();
        }

@GetMapping("/{id}")
public Result findOne(@PathVariable Integer id) {
        Exam res = examService.getById(id);
        if(res == null) {
                return GlobalExceptionHandler.buildApiResult(Constants.CODE_400,"Exam code is not exist",null);
        }
        return GlobalExceptionHandler.buildApiResult(Constants.CODE_200,"Request successfully！",res);
        }
        @GetMapping("/last")
        public Result Lastcode(){
        Exam res=examService.Lastexam();
                if(res == null) {
                        return GlobalExceptionHandler.buildApiResult(Constants.CODE_400,"Exam code is not exist",null);
                }
                return GlobalExceptionHandler.buildApiResult(Constants.CODE_200,"Request successfully！",res);
        }

@GetMapping("/page")
public Page<Exam> findPage(@RequestParam Integer pageNum,
@RequestParam Integer pageSize, @RequestParam String source,
                           @RequestParam String paperid) {
        QueryWrapper<Exam> queryWrapper = new QueryWrapper<>();
        if (!"".equals(source)) {
                queryWrapper.like("source", source);
        }
        if (!"".equals(paperid)) {
                int paperid_int = Integer.parseInt(paperid);
                queryWrapper.like("paperid", paperid_int);
        }
        queryWrapper.orderByDesc("examcode");
        return examService.page(new Page<>(pageNum, pageSize), queryWrapper);
        }

        }


