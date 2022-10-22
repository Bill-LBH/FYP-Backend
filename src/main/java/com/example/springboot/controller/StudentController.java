package com.example.springboot.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Constants;
import com.example.springboot.common.Result;
import com.example.springboot.entity.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.example.springboot.service.IStudentService;
import com.example.springboot.entity.Student;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

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
public Boolean delete(@PathVariable String id) {
        return studentService.removeById(id);
        }

@PostMapping("/del/batch")
public boolean deleteBatch(@RequestBody List<String> ids) {
        return studentService.removeByIds(ids);
        }

@GetMapping
public List<Student> findAll() {
        return studentService.list();
        }

@GetMapping("/{id}")
public Student findOne(@PathVariable String id) {
        return studentService.getById(id);
        }


@GetMapping("/page")
public Page<Student> findPage(@RequestParam Integer pageNum,
@RequestParam Integer pageSize) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return studentService.page(new Page<>(pageNum, pageSize), queryWrapper);
        }

        /**
         * 导出接口
         */
        @GetMapping("/export")
        public void export(HttpServletResponse response) throws Exception {
                // 从数据库查询出所有的数据
                List<Student> list = studentService.list();
                // 通过工具类创建writer 写出到磁盘路径
//        ExcelWriter writer = ExcelUtil.getWriter(filesUploadPath + "/用户信息.xlsx");
                // 在内存操作，写出到浏览器
                ExcelWriter writer = ExcelUtil.getWriter(true);
                //自定义标题别名

                // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
                writer.write(list, true);

                // 设置浏览器响应的格式
                response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
                String fileName = URLEncoder.encode("Student Information", "UTF-8");
                response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

                ServletOutputStream out = response.getOutputStream();
                writer.flush(out, true);
                out.close();
                writer.close();

        }
        @PostMapping("/import")
        public Boolean imp(MultipartFile file) throws Exception {
                InputStream inputStream = file.getInputStream();
                ExcelReader reader = ExcelUtil.getReader(inputStream);
                // 方式1：(推荐) 通过 javabean的方式读取Excel内的对象，但是要求表头必须是英文，跟javabean的属性要对应起来
        List<Student> list = reader.readAll(Student.class);

                // 方式2：忽略表头的中文，直接读取表的内容
//                直接读取表的内容List<List<Object>> list = reader.read(1);
//                List<Student> users = CollUtil.newArrayList();
//                for (List<Object> row : list) {
//                        User user = new User();
//                        user.setUsername(row.get(0).toString());
//                        user.setPassword(row.get(1).toString());
//                        user.setNickname(row.get(2).toString());
//                        user.setEmail(row.get(3).toString());
//                        user.setPhone(row.get(4).toString());
//                        user.setAddress(row.get(5).toString());
//                        user.setAvatarUrl(row.get(6).toString());
//                        users.add(user);
//                }

                studentService.saveBatch(list);
                return true;
        }
        }


