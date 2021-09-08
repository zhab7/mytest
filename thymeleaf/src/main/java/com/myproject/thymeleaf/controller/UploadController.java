//package com.myproject.thymeleaf.controller;
//
//import com.luhuiguo.fastdfs.domain.StorePath;
//import com.luhuiguo.fastdfs.service.FastFileStorageClient;
//import org.apache.commons.io.FilenameUtils;
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.fs.FSDataInputStream;
//import org.apache.hadoop.fs.FSDataOutputStream;
//import org.apache.hadoop.fs.FileSystem;
//import org.apache.hadoop.fs.Path;
//import org.apache.http.HttpResponse;
//import org.apache.http.entity.ContentType;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.ColumnMapRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.BufferedOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.net.URLEncoder;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//public class UploadController {
//    @Autowired
//    private FastFileStorageClient storageClient;
//    @Autowired
//    JdbcTemplate jdbcTemplate;
//
//    /**
//     * @param myFile 从浏览器提交过来
//     * @return
//     */
//    @PostMapping("/up")
//    public String upload(@RequestParam("myFile") MultipartFile myFile) throws IOException {
//        //获取文件后缀名
//        String extension = FilenameUtils.getExtension(myFile.getOriginalFilename());
//        //将要上传的文件存入FastDFS
//        StorePath sp = storageClient.uploadFile("group1", myFile.getInputStream(), myFile.getSize(), extension);
//        String sql = "insert into myfile(filename,groupname,filepath) values(?,?,?)";
//        jdbcTemplate.update(sql, myFile.getOriginalFilename(), sp.getGroup(), sp.getPath());
//        return sp.getFullPath();
//    }
//
//    @GetMapping("/dows/{id}")
//    public void download(@PathVariable String id, HttpServletResponse re) throws IOException {
////        List list = jdbcTemplate.query("select * from  myfile where id=" + id, new ColumnMapRowMapper());
//        List list = jdbcTemplate.query("select * from  myfile where id=" + id, new ColumnMapRowMapper());
//        Map map = (Map) list.get(0);
//        String fileName = URLEncoder.encode(map.get("filename").toString(), "UTF-8");
//        String groupName = map.get("groupname").toString();
//        String pathName = map.get("filepath").toString();
//        //告诉浏览器下载的文件名
//        re.setHeader("Content-Disposition", "attachment; filename=" + fileName + "");
//        //去linux下载对应的文件
//        byte[] bytes = storageClient.downloadFile(groupName, pathName);
//        re.getOutputStream().write(bytes);
//
//    }
//
//    @PostMapping("/newUpload")
//    public void newUpload(@RequestParam("file") MultipartFile file, @RequestParam String path) throws Exception {
//        /*// FIXME windows需要安装插件 winutils.exe
//        //1 创建连接
//        Configuration conf = new Configuration();
//        //2 连接端口
////        conf.set("fs.defaultFS", "hdfs://node01:9000");
//        URI uri = new URI("hdfs://192.168.159.128:9000");
//
//        //3 获取连接对象
//        FileSystem fs = FileSystem.get(uri, conf);
//        //本地文件上传到 hdfs
//        String fileName = file.getOriginalFilename();
//        Path newPath = new Path(path + "/" + fileName);
//        FSDataOutputStream outputStream = fs.create(newPath);
//        outputStream.write(file.getBytes());
//        outputStream.close();
//        fs.close();*/
//
//        URI uri = new URI("hdfs://node1:9000");
//        Configuration configuration = new Configuration();
//        configuration.set("dfs.client.use.datanode.hostname", "true");
//        String user = "root";
//        FileSystem fs = FileSystem.get(uri, configuration, user);
//
////        fs.copyFromLocalFile(new Path("D:\\print\\SO201911060336.tex"), new Path(path));
//
//        FSDataOutputStream fsDataOutputStream = fs.create(new Path(path));
//        fsDataOutputStream.write(file.getBytes());
//        fsDataOutputStream.close();
//        fs.close();
//    }
//
//    /*public static void createFile(String path, MultipartFile file) throws Exception {
//        if (StringUtils.isEmpty(path) || null == file.getBytes()) {
//            return;
//        }
//        String fileName = file.getOriginalFilename();
//        FileSystem fs = getFileSystem();
//        // 上传时默认当前目录，后面自动拼接文件的目录
//        Path newPath = new Path(path + "/" + fileName);
//        // 打开一个输出流
//        FSDataOutputStream outputStream = fs.create(newPath);
//        outputStream.write(file.getBytes());
//        outputStream.close();
//        fs.close();
//    }
//
//    public static FileSystem getFileSystem() throws Exception {
//        *//*
//        //通过这种方式设置java客户端访问hdfs的身份：会以 ypp 的身份访问 hdfs文件系统目录下的路径：/user/ypp 的目录
//        System.setProperty("HADOOP_USER_NAME","ypp");
//        Configuration configuration = new Configuration();
//        configuration.set("fs.defauleFS","hdfs://ypp:9090");
//        FileSystem fileSystem = FileSystem.get(configuration);
//         *//*
//
//     *//*
//        客户端去操作hdfs时是有一个用户身份的，默认情况下hdfs客户端api会从jvm中获取一个参数作为自己的用户身份
//        也可以在构造客户端fs对象时，通过参数传递进去
//        FileSystem fileSystem = FileSystem.get(new URI(hdfsPath), getConfiguration(), hdfsName);
//        *//*
//        FileSystem fileSystem = FileSystem.get(new URI(hdfsPath), getConfiguration());
//        return fileSystem;
//    }*/
//
//    @GetMapping("/newDownlaod")
//    public void newDownload(@RequestParam String src, @RequestParam String dst) throws URISyntaxException, IOException, InterruptedException {
//        FileSystem fs = getFileStream();
//        fs.copyToLocalFile(false, new Path(src), new Path(dst));
//        fs.close();
//    }
//
//    @GetMapping("/downloadStream")
//    public void downloadStream(@RequestParam String src, HttpServletResponse response) throws URISyntaxException, IOException, InterruptedException {
//        FileSystem fs = getFileStream();
//        FSDataInputStream inputStream = fs.open(new Path(src));
////        response.setContentType("video/mpeg4");
//        OutputStream os = new BufferedOutputStream(response.getOutputStream());
//        byte[] bytes = new byte[4096];
//        int length;
//        while ((length = inputStream.read(bytes)) > 0) {
//            os.write(bytes, 0, length);
//        }
//
//        inputStream.close();
//        os.close();
//        fs.close();
//    }
//
//    private FileSystem getFileStream() throws URISyntaxException, IOException, InterruptedException {
//        Configuration configuration = new Configuration();
//        configuration.set("dfs.client.use.datanode.hostname", "true");
//        URI uri = new URI("hdfs://node1:9000");
//        String user = "root";
//        return FileSystem.get(uri, configuration, user);
//    }
//}
//
//
