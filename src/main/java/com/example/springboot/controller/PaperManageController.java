package com.example.springboot.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Constants;
import com.example.springboot.common.Result;
import com.example.springboot.entity.*;
import com.example.springboot.exception.GlobalExceptionHandler;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.service.impl.FillQuestionServiceImpl;
import com.example.springboot.service.impl.JudgeQuestionServiceImpl;
import com.example.springboot.service.impl.MultiQuestionServiceImpl;
import com.example.springboot.vo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.example.springboot.service.IPaperManageService;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
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

    @Autowired
    MultiQuestionServiceImpl multiQuestionService;

    @Autowired
    FillQuestionServiceImpl fillQuestionService;

    @Autowired
    JudgeQuestionServiceImpl judgeQuestionService;

    // 新增或者更新
    @PostMapping
    public boolean save(@RequestBody PaperManage paperManage) {
        return paperManageService.saveOrUpdate(paperManage);
    }

    @PostMapping("/item")
    public Result ItemController(@RequestBody Item item) {
        Integer changeNumber = item.getChangeNumber();
        // 填空题
        Integer fillNumber = item.getFillNumber();
        // 判断题
        Integer judgeNumber = item.getJudgeNumber();
        //出卷id
        Integer paperId = item.getPaperId();

        // 选择题数据库获取
        List<Integer> changeNumbers = multiQuestionService.findBySubject(item.getSubject(), changeNumber);
        if (changeNumbers == null) {
            return GlobalExceptionHandler.buildApiResult(Constants.CODE_400, "Failed to fetch multiple-choice question database", null);
        }
        for (Integer number : changeNumbers) {
            PaperManage paperManage = new PaperManage(paperId, 1, number);
            int index = paperManageService.add(paperManage);
            if (index == 0)
                return GlobalExceptionHandler.buildApiResult(Constants.CODE_400, "Failed to save multiple-choice question", null);
        }

        // 填空题
        List<Integer> fills = fillQuestionService.findBySubject(item.getSubject(), fillNumber);
        if (fills == null)
            return GlobalExceptionHandler.buildApiResult(Constants.CODE_400, "Failed to fetch fill-in question database", null);
        for (Integer fillNum : fills) {
            PaperManage paperManage = new PaperManage(paperId, 2, fillNum);
            int index = paperManageService.add(paperManage);
            if (index == 0)
                return GlobalExceptionHandler.buildApiResult(Constants.CODE_400, "Failed to save fill_in question", null);
        }
        // 判断题
        List<Integer> judges = judgeQuestionService.findBySubject(item.getSubject(), judgeNumber);
        if (fills == null)
            return GlobalExceptionHandler.buildApiResult(Constants.CODE_400, "Failed to fetch judge question database", null);
        for (Integer judge : judges) {
            PaperManage paperManage = new PaperManage(paperId, 3, judge);
            int index = paperManageService.add(paperManage);
            if (index == 0)
                return GlobalExceptionHandler.buildApiResult(Constants.CODE_400, "Failed to save judge question", null);
        }

        return GlobalExceptionHandler.buildApiResult(Constants.CODE_200, "Form paper successfully", null);
    }



    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return paperManageService.removeByIds(ids);
    }

    @GetMapping
    public List<PaperManage> findAll() {
        return paperManageService.list();
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id){
        QueryWrapper<PaperManage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("questionid",id);
        return paperManageService.remove(queryWrapper);
    }

    @PostMapping("/paperManage")
    public Result add(@RequestBody PaperManage paperManage) {
        int res = paperManageService.add(paperManage);
        if (res != 0) {
            return GlobalExceptionHandler.buildApiResult(Constants.CODE_200, "Add successfully", res);
        }
        return GlobalExceptionHandler.buildApiResult(Constants.CODE_200, "Add failed", res);
    }

    @GetMapping("/{id}/{paperid}/{type}")
    public Result findOne(@PathVariable Integer id, @PathVariable Integer paperid, @PathVariable Integer type) {
        QueryWrapper<PaperManage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("questionid",id);
        queryWrapper.eq("paperid",paperid);
        queryWrapper.eq("questiontype",type);
        PaperManage res= paperManageService.getOne(queryWrapper);
        if(res == null) {
            return GlobalExceptionHandler.buildApiResult(Constants.CODE_400,"Exam code is not exist",null);
        }
        return GlobalExceptionHandler.buildApiResult(Constants.CODE_200,"Request successfully！",res);
    }

    @GetMapping("/page")
    public Page<PaperManage> findPage(@RequestParam Integer pageNum,
                                      @RequestParam Integer pageSize) {
        QueryWrapper<PaperManage> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("paperid");
        return paperManageService.page(new Page<>(pageNum, pageSize), queryWrapper);
    }

    @GetMapping("/paper/{paperId}")
    public Map<Integer, List<?>> findById(@PathVariable("paperId") Integer paperId) {
        List<MultiQuestion> multiQuestionRes = multiQuestionService.findByIdAndType(paperId);   //选择题题库 1
        List<FillQuestion> fillQuestionsRes = fillQuestionService.findByIdAndType(paperId);     //填空题题库 2
        List<JudgeQuestion> judgeQuestionRes = judgeQuestionService.findByIdAndType(paperId);   //判断题题库 3
        Map<Integer, List<?>> map = new HashMap<>();
        map.put(1,multiQuestionRes);
        map.put(2,fillQuestionsRes);
        map.put(3,judgeQuestionRes);
        return  map;
    }


}


