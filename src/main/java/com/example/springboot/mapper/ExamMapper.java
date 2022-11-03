package com.example.springboot.mapper;

import com.example.springboot.entity.Exam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 林伯翰
 * @since 2022-10-15
 */
public interface ExamMapper extends BaseMapper<Exam> {
    @Select("select examcode from exam order by examcode desc limit 1")
    Exam LastExam();

    @Update("UPDATE exam set totalscore = #{total} where paperid =#{paperid}")
    boolean updatescore(int total,String paperid);

}
