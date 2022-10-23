package com.example.springboot.mapper;

import com.example.springboot.entity.FillQuestion;
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
public interface FillQuestionMapper extends BaseMapper<FillQuestion> {

    @Select("select questionid from fill_question where subject = #{subject} order by rand() desc limit #{pageNo}")
    List<Integer> findBySubject(String subject, Integer pageNo);

    @Options(useGeneratedKeys = true,keyProperty ="questionid" )
    @Insert("insert into fill_question(subject,question,answer,analysis,score,level,section) values " +
            "(#{subject,},#{question},#{answer},#{analysis},#{score},#{level},#{section})")
    int add(FillQuestion fillQuestion);

    @Select("select questionid from fill_question order by questionid desc limit 1")
    FillQuestion findOnlyQuestionId();

    @Select("select * from fill_question where questionid in (select questionid from paper_manage where questiontype = 2 and paperid = #{paperId})")
    List<FillQuestion> findByIdAndType(Integer paperId);



}
