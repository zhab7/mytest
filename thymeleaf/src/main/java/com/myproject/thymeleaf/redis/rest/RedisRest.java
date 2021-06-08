package com.myproject.thymeleaf.redis.rest;

import com.myproject.thymeleaf.redis.handler.RedisHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author zhanjianjian
 * @since 2021/5/7
 */
@RestController
@RequestMapping("/rest/redis")
public class RedisRest {

    @Resource
    private RedisHandler redisHandler;

    @GetMapping("/test")
    public String redisTest() {
        redisHandler.test();
        return "success";
    }

    @GetMapping("/vote")
    public String redisVote(@RequestParam String bookId) {
        redisHandler.vote(bookId);
        return "success";
    }

    @GetMapping("/selectVote")
    public String redisSelectVote() {
        redisHandler.selectVote();
        return "success";
    }

    @GetMapping("/getFile")
    public void fileStream(HttpServletResponse response) {
        File file = new File("D:\\project\\thymeleaf\\mytest\\thymeleaf\\src\\main\\resources\\static\\video\\test.mp4");
        try {
            FileInputStream fis = new FileInputStream(file);
            OutputStream os = new BufferedOutputStream(response.getOutputStream());
            byte[] bytes = new byte[2048];
            int length;
            while ((length = fis.read(bytes)) > 0) {
                os.write(bytes, 0, length);
            }
            fis.close();
            os.flush();
            os.close();
        } catch (IOException e) {
            System.out.println("e = " + e);
        }
    }

}
