package com.example.springboot.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
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
  @ApiModel(value = "Exam对象", description = "")
public class Exam implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "examcode",type = IdType.AUTO)
      private int examcode;

    private String source;

    private String paperid;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime examdate;

    private Integer totaltime;

    private String grade;

    private String term;

    private String major;

    private Integer totalscore;

      @ApiModelProperty("考试类型")
      private String type;

      @ApiModelProperty("考生须知")
      private String tips;


}
