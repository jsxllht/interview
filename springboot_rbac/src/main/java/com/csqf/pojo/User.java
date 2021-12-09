package com.csqf.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;

    private Long phone;

    private String wxId;

    private String wxNickname;

    private Integer roleid;

    private String roles;

    private String avatarUrl;

    private Date createTime;

    private Date updateTime;

    private Integer bonus;

    private Boolean isvip;

    private String userName;

    private String userPassword;

    private BigDecimal userMoney;

    private Long version;

    private String province;

    private Byte isDeleted;

}