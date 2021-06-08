package com.myproject.thymeleaf.redis.service.impl;

import com.myproject.thymeleaf.redis.service.FileService;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * @author zhanjianjian
 * @since 2021/6/7
 */
@Service
public class FileServiceImpl implements FileService {

    // 将文件传到redis里面
    public void fileTest() throws FileNotFoundException {
//        InputStream inputStream = new FileInputStream("./static/video/test.mp4");

/*
        File file = new File("./static/video/test.mp4");
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

        }
*/

    }

}
