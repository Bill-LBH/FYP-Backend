package com.example.springboot.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Constants;
import com.example.springboot.common.Result;
import com.example.springboot.entity.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.example.springboot.config.service.IStudentService;
import com.example.springboot.entity.Student;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 林伯翰
 * @since 2022-10-09
 */
@RestController
@RequestMapping("/student")
public class StudentController {
@Autowired
private IStudentService studentService;

@PostMapping("/login")
public Result login(@RequestBody StudentDTO studentDTO) {
        String Id = studentDTO.getId();
        String password = studentDTO.getPassword();
        if (StrUtil.isBlank(Id) || StrUtil.isBlank(password)) {
                return Result.error(Constants.CODE_400,"Argument error");
        }
        StudentDTO dto= studentService.login(studentDTO);
        return Result.success(dto);
}
// 新增或者更新
@PostMapping
public boolean save(@RequestBody Student student) {
        return studentService.saveOrUpdate(student);
        }

@DeleteMapping("/{id}")
public Boolean delete(@PathVariable Integer id) {
        return studentService.removeById(id);
        }

@PostMapping("/del/batch")
public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return studentService.removeByIds(ids);
        }

@GetMapping
public List<Student> findAll() {
        return studentService.list();
        }

@GetMapping("/{id}")
public Student findOne(@PathVariable Integer id) {
        return studentService.getById(id);
        }

@GetMapping("/page")
public Page<Student> findPage(@RequestParam Integer pageNum,
@RequestParam Integer pageSize) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return studentService.page(new Page<>(pageNum, pageSize), queryWrapper);
        }
        }


