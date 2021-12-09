package com.csqf.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("用户实体类")
public class UserListDTO implements Serializable {
    @ApiModelProperty("用户id")
    private Long id;
    @ApiModelProperty("用户积分")
    private Integer bonus;
    @ApiModelProperty("用户电话")
    private Long phone;
    @ApiModelProperty("是否是vip")
    private Boolean isvip;
    @ApiModelProperty("用户角色")
    private String roles;

}
