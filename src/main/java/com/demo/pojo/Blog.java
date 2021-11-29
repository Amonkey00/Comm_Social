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
    private String blogContext;
    private Date blogDate;
    private int times;

    public Blog(int userId, String blogTitle, String blogContext, Date currentTime, int i) {
        this.userId=userId;
        this.blogTitle=blogTitle;
        this.blogContext=blogContext;
        this.blogDate=currentTime;
        this.times=i;
    }
}
