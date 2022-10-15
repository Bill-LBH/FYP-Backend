package com.example.springboot.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.example.springboot.service.ITeacherService;
import com.example.springboot.entity.Teacher;
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
@RequestMapping("/teacher")
public class TeacherController {
@Autowired
private ITeacherService teacherService;

// 新增或者更新
@PostMapping
public boolean save(@RequestBody Teacher teacher) {
        return teacherService.saveOrUpdate(teacher);
        }

@DeleteMapping("/{id}")
public Boolean delete(@PathVariable Integer id) {
        return teacherService.removeById(id);
        }

@PostMapping("/del/batch")
public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return teacherService.removeByIds(ids);
        }

@GetMapping
public List<Teacher> findAll() {
        return teacherService.list();
        }

@GetMapping("/{id}")
public Teacher findOne(@PathVariable Integer id) {
        return teacherService.getById(id);
        }

@GetMapping("/page")
public Page<Teacher> findPage(@RequestParam Integer pageNum,
@RequestParam Integer pageSize) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return teacherService.page(new Page<>(pageNum, pageSize), queryWrapper);
        }

        }


