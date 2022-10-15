package com.example.springboot.entity;

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
  @ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

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
