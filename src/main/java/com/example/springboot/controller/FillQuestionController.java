package com.example.springboot.controller;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Constants;
import com.example.springboot.common.Result;
import com.example.springboot.exception.GlobalExceptionHandler;
import com.example.springboot.service.impl.PaperManageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.example.springboot.service.IFillQuestionService;
import com.example.springboot.entity.FillQuestion;
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

@PostMapping("/del/batch/")
public Result deleteBatch(@RequestBody List<Integer> ids) {
        Map<String,Object> fillmap=new HashMap<>();
        for(int i=0;i<ids.size();i++){
                fillmap.put("questiontype",2);
                System.out.println(ids.size());
                System.out.println(ids.get(i));
                fillmap.put("questionid",ids.get(i));
                paperManageService.removeByMap(fillmap);
        }
        fillQuestionService.removeByIds(ids);
        return Result.success();
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

        /**
         * 导出接口
         */
        @GetMapping("/export")
        public void export(HttpServletResponse response) throws Exception {
                // 从数据库查询出所有的数据
                List<FillQuestion> list = fillQuestionService.list();
                // 通过工具类创建writer 写出到磁盘路径
//        ExcelWriter writer = ExcelUtil.getWriter(filesUploadPath + "/用户信息.xlsx");
                // 在内存操作，写出到浏览器
                ExcelWriter writer = ExcelUtil.getWriter(true);
                //自定义标题别名

                // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
                writer.write(list, true);

                // 设置浏览器响应的格式
                response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
                String fileName = URLEncoder.encode("Fill_in question bank", "UTF-8");
                response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

                ServletOutputStream out = response.getOutputStream();
                writer.flush(out, true);
                out.close();
                writer.close();

        }

        /**
         * excel 导入
         * @param file
         * @throws Exception
         */
        @PostMapping("/import")
        public Boolean imp(MultipartFile file) throws Exception {
                InputStream inputStream = file.getInputStream();
                ExcelReader reader = ExcelUtil.getReader(inputStream);
                // 方式1：(推荐) 通过 javabean的方式读取Excel内的对象，但是要求表头必须是英文，跟javabean的属性要对应起来
        List<FillQuestion> list = reader.readAll(FillQuestion.class);

                // 方式2：忽略表头的中文，直接读取表的内容
//                List<List<Object>> list = reader.read(1);
//                List<FillQuestion> questions = CollUtil.newArrayList();
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

                fillQuestionService.saveBatch(list);
                return true;
        }

        }


