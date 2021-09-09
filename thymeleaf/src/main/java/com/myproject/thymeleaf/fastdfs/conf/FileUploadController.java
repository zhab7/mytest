package com.myproject.thymeleaf.fastdfs.conf;

import com.myproject.thymeleaf.model.entity.FileAttachment;
import com.myproject.thymeleaf.service.FileAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/file")
public class FileUploadController {

    @Autowired
    private FastDFSClient fastDFSClient;
    @Resource
    private FileAttachmentService fileAttachmentService;

    /**
     * 上传
     *
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam String comment) throws IOException {
        return fastDFSClient.uploadFile(file, comment);
    }

    /**
     * 下载
     *
     * @param fileUrl
     * @param response
     * @throws IOException
     */
    @RequestMapping("/download")
    public void downloadFile(String fileUrl, HttpServletResponse response) throws IOException {
        byte[] bytes = fastDFSClient.downloadFile(fileUrl);
        FileAttachment fileAttachment = fileAttachmentService.getByFilePath(fileUrl);
        if (fileAttachment == null) {
            return;
        }
        // URLEncoder.encode会将空格转成+号，replace("+","%20")是将+号再次转换成空格。不用URLEncoder.encode时会乱码，下载的文件名一直是download
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileAttachment.getFileName(), "UTF-8").replace("+", "%20"));
        response.setCharacterEncoding("UTF-8");
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            outputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}