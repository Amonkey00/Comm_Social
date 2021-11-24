package com.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Blog {
    private int blogId;
    private int userId;
    private String blogTitle;
    private Date blogDate;
    private int times;
    private String blogContext;

}
