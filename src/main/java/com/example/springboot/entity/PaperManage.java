package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author 林伯翰
 * @since 2022-10-15
 */
@Getter
@Setter
  @TableName("paper_manage")
@ApiModel(value = "PaperManage对象", description = "")
@AllArgsConstructor
public class PaperManage implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "paperid",type = IdType.NONE)
    private Integer paperid;

    private Integer questiontype;

    private Integer questionid;


}
