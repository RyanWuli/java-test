package com.example.redishash.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Ryan
 * @Date: 2020/5/11 17:01
 * @Version: 1.0
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {

    public static final String TABLE = "t_user";

    private String name;
    private String address;
    private Integer age;

}
