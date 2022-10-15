package com.example.springboot.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.example.springboot.service.IScoreService;
import com.example.springboot.entity.Score;
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
@RequestMapping("/score")
public class ScoreController {
@Autowired
private IScoreService scoreService;

// 新增或者更新
@PostMapping
public boolean save(@RequestBody Score score) {
        return scoreService.saveOrUpdate(score);
        }

@DeleteMapping("/{id}")
public Boolean delete(@PathVariable Integer id) {
        return scoreService.removeById(id);
        }

@PostMapping("/del/batch")
public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return scoreService.removeByIds(ids);
        }

@GetMapping
public List<Score> findAll() {
        return scoreService.list();
        }

@GetMapping("/{id}")
public Score findOne(@PathVariable Integer id) {
        return scoreService.getById(id);
        }

@GetMapping("/page")
public Page<Score> findPage(@RequestParam Integer pageNum,
@RequestParam Integer pageSize) {
        QueryWrapper<Score> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return scoreService.page(new Page<>(pageNum, pageSize), queryWrapper);
        }

        }


