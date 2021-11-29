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
public class Comment {
    // JPA有自增注解。
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;
    private int userId;
    private int blogId;
    private String commentContext;

    public Comment(int userId, int blogId, String commentContext) {
        this.userId=userId;
        this.blogId=blogId;
        this.commentContext=commentContext;
    }
}
