package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
  @TableName("fill_question")
@ApiModel(value = "FillQuestion对象", description = "")
public class FillQuestion implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "questionid",type = IdType.NONE)
      private Integer questionid;

    private String subject;

    private String question;

    private String answer;

    private String analysis;

    private Integer score;

    private String section;

    private String level;


}
