package com.csqf.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActionMenusDTO {
    private Integer rightid;

    private String rightCode;

    private String righttext;

    private Integer righttype;

    private String righturl;

    private Integer parentid;

    private List<ActionMenusDTO> children;
}
