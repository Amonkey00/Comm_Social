package com.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Blog {
    private int blogId;
    private int userId;
    private String blogTitle;
    private int times;
    private String blogContext;

}
