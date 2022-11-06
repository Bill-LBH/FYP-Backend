package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.entity.Score;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 林伯翰
 * @since 2022-10-15
 */
public interface ScoreMapper extends BaseMapper<Score> {
    @Insert("insert into score(examcode,studentid,subject,score,answerdate) values(#{examcode},#{studentid},#{subject},#{score},#{answerdate})")
    int add(Score score);
    @Select("select scoreid,examcode,studentid,subject,score,answerdate from score where studentid = #{studentId} order by scoreid desc")
    IPage<Score> findById(Page<?> page, String studentId);
}
