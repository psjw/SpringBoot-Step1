package com.example.hello.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SwaggerUserRequestDto {
    @ApiModelProperty(value = "사용자의이름",example = "steve",required = true)
    private String name;
    @ApiModelProperty(value = "사용자의나이",example = "40",required = true)
    private int age;
}
