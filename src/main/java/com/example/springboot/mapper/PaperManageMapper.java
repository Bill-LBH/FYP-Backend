package com.example.springboot.mapper;

import com.example.springboot.entity.PaperManage;
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
public interface PaperManageMapper extends BaseMapper<PaperManage> {
    @Insert("insert into paper_manage(paperid,questiontype,questionid) values " +
            "(#{paperid},#{questiontype},#{questionid})")
    int add(PaperManage paperManage);

}
