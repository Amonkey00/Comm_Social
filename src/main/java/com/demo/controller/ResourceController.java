package com.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@RestController
@RequestMapping("/resources")
public class ResourceController {

    @Value("${path.default-avatar-path}")
    private String defaultAvatarPath;

    @GetMapping("/images/{filename}")
    public void getResource(@PathVariable("filename") String filename, ServletResponse response) throws IOException {
        filename = defaultAvatarPath + "\\" + filename;
        String suffix = filename.substring(filename.lastIndexOf("."));
        response.setContentType("image/"+suffix);

        OutputStream os = null;
        FileInputStream fis = null;
        try{
            os = response.getOutputStream();
            fis = new FileInputStream(filename);
            byte[] buffer = new byte[1024];
            int b = 0;
            while((b=fis.read(buffer))!=-1){
                os.write(buffer,0,b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            os.close();
            fis.close();
        }
    }
}
