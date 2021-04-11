package com.sensible.tacocloud.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * taco类
 */
@Data
public class Taco {
    private Long id;

    @NotNull
    @Size(min = 5,message = "名称长度必须至少5个字符")
    private String name;

    @Size(min = 1,message = "至少选择一中配料")
    private List<String> ingredients;

    private Date createAt;
}
