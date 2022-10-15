package com.example.springboot.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
<<<<<<< HEAD
 * @since 2022-10-15
=======
 * @since 2022-10-08
>>>>>>> parent of e5c5e53 (完成所有Entity的crud功能，完成登录界面的设计，完成了路由管理功能)
 */
@Getter
@Setter
  @ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
  @TableId(value = "id",type = IdType.NONE)
      @ApiModelProperty("user id")
        private String id;

      @ApiModelProperty("user type")
      private String usertype;

      @ApiModelProperty("user password")
      private String password;

      @ApiModelProperty("user name")
      private String username;

      @ApiModelProperty("user gender")
      private String gender;

      @ApiModelProperty("user address")
      private String address;

      @ApiModelProperty("user email")
      private String email;

      @ApiModelProperty("user phone number")
      private String phone;


}
