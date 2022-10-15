package com.example.springboot.entity;

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
  @TableName("judge_question")
@ApiModel(value = "JudgeQuestion对象", description = "")
public class JudgeQuestion implements Serializable {

    private static final long serialVersionUID = 1L;

      private Integer questionid;

    private String subject;

    private String question;

    private String answer;

    private String analysis;

    private Integer score;

    private String section;

    private String level;


}
