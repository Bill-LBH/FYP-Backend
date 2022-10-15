package com.example.springboot.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
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

      private String examcode;

    private String source;

    private String paperid;

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
