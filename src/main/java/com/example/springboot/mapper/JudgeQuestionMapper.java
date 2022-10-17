package com.example.springboot.mapper;

import com.example.springboot.entity.JudgeQuestion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 林伯翰
 * @since 2022-10-15
 */
public interface JudgeQuestionMapper extends BaseMapper<JudgeQuestion> {
    @Select("select questionid from judge_question  where subject=#{subject}  order by rand() desc limit #{pageNo}")
    List<Integer> findBySubject(String subject, Integer pageNo);

    @Select("select questionid from judge_question order by questionid desc limit 1")
    JudgeQuestion findOnlyQuestionId();

    @Insert("insert into judge_question(subject,question,answer,analysis,level,section) values " +
            "(#{subject},#{question},#{answer},#{analysis},#{level},#{section})")
    int add(JudgeQuestion judgeQuestion);

}
