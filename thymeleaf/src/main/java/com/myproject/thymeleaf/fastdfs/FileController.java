//package com.myproject.thymeleaf.fastdfs;
//
//import org.csource.common.NameValuePair;
//import org.csource.fastdfs.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//
//@RestController
//@RequestMapping("/file")
//public class FileController {
//
//    private Logger log = LoggerFactory.getLogger(FileController.class);
//    @Autowired
//    private TrackerClient trackerClient;
//
//    @PostMapping("/upload")
//    public String uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
//        if (file == null) {
//            throw new RuntimeException("文件不能为空");
//        }
//        //1.获取文件的完整名称
//        String filename = file.getOriginalFilename();
//        if (StringUtils.isEmpty(filename)) {
//            throw new RuntimeException("文件不存在");
//        }
//        //2.获取文件的扩展名称
//        String extName = filename.substring(filename.lastIndexOf(".") + 1);
//        log.info("文件的全名：" + filename + "    文件的扩展名：" + extName);
//        NameValuePair[] metaList = new NameValuePair[1];
//        metaList[0] = new NameValuePair("fileName", filename);
//        //3.创建trackerServer
//        TrackerServer trackerServer = trackerClient.getConnection();
//        // 4、创建一个 StorageServer 的引用，值为 null
//        StorageServer storageServer = null;
//        // 5、创建一个 StorageClient 对象，需要两个参数 TrackerServer 对象、StorageServer 的引用
//        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
//        // 6、使用 StorageClient 对象上传图片。
//        String[] strings = storageClient.upload_file(file.getBytes(), extName, metaList);
//        return "http://212.129.135.144:8888/" + strings[0] + "/" + strings[1];
//
//    }
//}