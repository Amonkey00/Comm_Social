package com.demo.controller;

import com.demo.config.JsonResult;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/Combination")
//@Api(value = "Combination Test")
public class BlogController {

    @PostMapping(value="/getRecentBlog")
    @ResponseBody   //接受前端json格式的数据
    public JsonResult getRecentBlog(){


        return new JsonResult();
    }


}
