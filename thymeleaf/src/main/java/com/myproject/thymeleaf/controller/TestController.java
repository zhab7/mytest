package com.myproject.thymeleaf.controller;


import com.myproject.thymeleaf.model.annotation.Log;
import io.swagger.annotations.Api;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "测试Controller")
@RestController
public class TestController {
    @Resource
    private RestTemplate restTemplate;

    @Log("执行方法1")
    @GetMapping("/one")
    public void methodOne(String name) { }

    @Log("执行方法2")
    @GetMapping("/two")
    public void methodTwo() throws InterruptedException {
        Thread.sleep(2000);
    }

    @Log("执行方法3")
    @GetMapping("/three")
    public void methodThree(String name, String age) { }

    @GetMapping("/test00")
    public void test00() {
        String url = "http://192.168.0.13:8011/base/customer/search";
//        String url = "http://192.168.0.13:8011/base/customer/search?serviceProviderRefId=mQiVDIwzvr3Y";
//        String baseUrl = "http://www.baidu.com";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("serviceProviderRefId", "mQiVDIwzvr3Y");
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        URI uri = builder.queryParams(params).build().encode().toUri();
        Object forObject = restTemplate.exchange(uri, HttpMethod.GET,null, Object.class);
        System.out.println("exchange = " + forObject);
        System.out.println(" ssssss " );
    }
}