package com.example.springboot.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.example.springboot.service.IPaperManageService;
import com.example.springboot.entity.PaperManage;
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
@RequestMapping("/paper-manage")
public class PaperManageController {
@Autowired
private IPaperManageService paperManageService;

// 新增或者更新
@PostMapping
public boolean save(@RequestBody PaperManage paperManage) {
        return paperManageService.saveOrUpdate(paperManage);
        }

@DeleteMapping("/{id}")
public Boolean delete(@PathVariable Integer id) {
        return paperManageService.removeById(id);
        }

@PostMapping("/del/batch")
public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return paperManageService.removeByIds(ids);
        }

@GetMapping
public List<PaperManage> findAll() {
        return paperManageService.list();
        }

@GetMapping("/{id}")
public PaperManage findOne(@PathVariable Integer id) {
        return paperManageService.getById(id);
        }

@GetMapping("/page")
public Page<PaperManage> findPage(@RequestParam Integer pageNum,
@RequestParam Integer pageSize) {
        QueryWrapper<PaperManage> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("paperid");
        return paperManageService.page(new Page<>(pageNum, pageSize), queryWrapper);
        }

        }


