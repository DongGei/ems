package com.yusael.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
//实体类
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
//管理员
public class User {
    private String id;
    private String username;
    private String realname;
    private String password;
    private String sex;
}

