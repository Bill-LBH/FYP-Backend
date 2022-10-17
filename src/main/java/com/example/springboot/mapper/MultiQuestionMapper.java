package com.example.springboot.mapper;

import com.example.springboot.entity.MultiQuestion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
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
public interface MultiQuestionMapper extends BaseMapper<MultiQuestion> {
    @Select("select questionid from multi_question  where subject =#{subject} order by rand() desc limit #{pageNo}")
    List<Integer> findBySubject(String subject, Integer pageNo);

    @Select("select questionid from multi_question order by questionid desc limit 1")
    MultiQuestion findOnlyQuestionId();

    @Options(useGeneratedKeys = true,keyProperty = "questionid")
    @Insert("insert into multi_question(subject,question,answera,answerb,answerc,answerd,rightanswer,analysis,section,level) " +
            "values(#{subject},#{question},#{answera},#{answerb},#{answerc},#{answerd},#{rightanswer},#{analysis},#{section},#{level})")
    int add(MultiQuestion multiQuestion);

}
