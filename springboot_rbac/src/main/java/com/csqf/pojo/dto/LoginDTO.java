package com.csqf.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDTO {

    private Long id;
    private Integer roleid;
    private String avatarUrl;
    private String userName;
    private String token;
}
