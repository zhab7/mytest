package com.myproject.thymeleaf.controller;

import com.luhuiguo.fastdfs.domain.StorePath;
import com.luhuiguo.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@RestController
public class UploadController {
    @Autowired
    private FastFileStorageClient storageClient;
    @Autowired
    JdbcTemplate jdbcTemplate;
    /**
     *
     * @param myFile  从浏览器提交过来
     * @return
     */
    @PostMapping("/up")
    public String upload(@RequestParam("myFile") MultipartFile myFile) throws IOException {
   		 //获取文件后缀名
        String extension = FilenameUtils.getExtension(myFile.getOriginalFilename());
        //将要上传的文件存入FastDFS
        StorePath sp=storageClient.uploadFile("group1",myFile.getInputStream(),myFile.getSize(),extension);
        String sql="insert into myfile(filename,groupname,filepath) values(?,?,?)";
        jdbcTemplate.update(sql,myFile.getOriginalFilename(),sp.getGroup(),sp.getPath());
        return sp.getFullPath();
    }
    @GetMapping("/dows/{id}")
    public void download(@PathVariable String id, HttpServletResponse re) throws IOException {
//        List list = jdbcTemplate.query("select * from  myfile where id=" + id, new ColumnMapRowMapper());
        List list = jdbcTemplate.query("select * from  myfile where id=" + id, new ColumnMapRowMapper());
        Map map=(Map) list.get(0);
        String fileName= URLEncoder.encode(map.get("filename").toString(),"UTF-8");
        String groupName=map.get("groupname").toString();
        String pathName=map.get("filepath").toString();
        //告诉浏览器下载的文件名
        re.setHeader("Content-Disposition","attachment; filename="+fileName+"");
		//去linux下载对应的文件
        byte[] bytes = storageClient.downloadFile(groupName, pathName);
        re.getOutputStream().write(bytes);

    }
}


