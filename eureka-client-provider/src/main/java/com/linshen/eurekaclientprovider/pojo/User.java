package com.linshen.eurekaclientprovider.pojo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @version V1.0
 * @author: lin_shen
 * @date: 2018/4/22
 * @Description: TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class User implements Serializable {
    private static final long serialVersionUID = -3579895493908682384L;
    private Long id;
    private String name;
    private Integer age;
}
