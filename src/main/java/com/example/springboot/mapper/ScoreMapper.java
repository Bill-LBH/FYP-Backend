package com.example.springboot.mapper;

import com.example.springboot.entity.Score;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;

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

}
