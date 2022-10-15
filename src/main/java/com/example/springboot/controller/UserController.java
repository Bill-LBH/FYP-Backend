package com.example.springboot.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.entity.User;
import com.example.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 林伯翰
<<<<<<< HEAD
 * @since 2022-10-15
=======
 * @since 2022-10-08
>>>>>>> parent of e5c5e53 (完成所有Entity的crud功能，完成登录界面的设计，完成了路由管理功能)
 */
@RestController
@RequestMapping("/user")
public class UserController {
@Autowired
private IUserService userService;

// 新增或者更新
@PostMapping
public boolean save(@RequestBody User user) {
        return userService.saveOrUpdate(user);
        }

@DeleteMapping("/{id}")
public Boolean delete(@PathVariable String id) {
        return userService.removeById(id);
        }

@PostMapping("/del/batch")
public boolean deleteBatch(@RequestBody List<String> ids) {
        return userService.removeByIds(ids);
        }

@GetMapping
public List<User> findAll() {
        return userService.list();
        }

@GetMapping("/{id}")
public User findOne(@PathVariable String id) {
        return userService.getById(id);
        }

@GetMapping("/page")
public Page<User> findPage(@RequestParam Integer pageNum,
@RequestParam Integer pageSize) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return userService.page(new Page<>(pageNum, pageSize), queryWrapper);
        }

        }


