package com.myproject.thymeleaf.es.rest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.myproject.thymeleaf.es.bean.vo.EsTestVo;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.assertj.core.util.Lists;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.ActiveShardCount;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author zhanjianjian
 * @since 2021/3/25
 */

@RestController
@RequestMapping("/es/test")
public class EsTestRest {

    @GetMapping("/creat")
    public String createdIndex() throws IOException {

        // 暂时不判断索引是否存在
        Map<String, Object> mappingMap = new HashMap<>();
        mappingMap.put("dynamic", "false");
        Map<String, Object> propertiesMap = new HashMap<>();
        Map<String, String> fildTypeMap = new HashMap<>();
        Field[] fields = EsTestVo.class.getFields();

        for (Field field : fields) {
            if (field.getType().equals(String.class) || field.getType().equals(List.class)) {
                fildTypeMap.put("type", "keyword");
                propertiesMap.put(field.getName(), fildTypeMap);
            } else if (field.getType().equals(Date.class)) {
                fildTypeMap.put("type", "long");
                propertiesMap.put(field.getName(), fildTypeMap);
            }
        }
        Map<String, Object> objectValueMap = new HashMap<>();
//        objectValueMap.put("properties")
//        objectValueMap.put("type", "nested");

//        for (String key : (propertiesMap.keySet())) {
//            properties.put(key, otherParams.get(key));
//        }
        mappingMap.put("properties", propertiesMap);

        CreateIndexRequest createIndexRequest = new CreateIndexRequest("sc");

        createIndexRequest.alias(new Alias("bieming"));
        createIndexRequest.settings(Settings.builder()
                .put("index.number_of_shards", 3)   //分片数
                .put("index.number_of_replicas", 2)); //备份数)

        createIndexRequest.mapping("doc", mappingMap);
        createIndexRequest.masterNodeTimeout(TimeValue.timeValueMinutes(2));
        createIndexRequest.waitForActiveShards(ActiveShardCount.DEFAULT);

        RestHighLevelClient client = getClient();

        client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        return "finish";
    }

    private RestHighLevelClient getClient() {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials("elastic", "111111"));

        RestClientBuilder clientBuilder = RestClient.builder(new HttpHost("192.168.159.128", 9200)).setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
            @Override
            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpAsyncClientBuilder) {
                return httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
            }
        });
        return new RestHighLevelClient(clientBuilder);
    }

    @GetMapping("/saveTest")
    public String saveTest() throws IOException {

        EsTestVo esTestVo = new EsTestVo();
        esTestVo.setName("test");
        esTestVo.setAge(15);
        esTestVo.setHobbies(Lists.newArrayList("play", "study"));

        JSONObject o = (JSONObject) JSON.toJSON(esTestVo);

        IndexRequest indexRequest = new IndexRequest("sc").id(esTestVo.getName()).type("doc").source(o.toJSONString(), XContentType.JSON).opType(DocWriteRequest.OpType.CREATE);
        getClient().index(indexRequest, RequestOptions.DEFAULT);
        return "finish";
    }

    @GetMapping("/select")
    public String selectTest() throws IOException {

        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.termQuery("name", "test"));

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQuery);

        SearchRequest searchRequest = new SearchRequest("sc");
        searchRequest.source(searchSourceBuilder);

        SearchResponse search = getClient().search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = search.getHits();
        Iterator<SearchHit> iterator = hits.iterator();
        while (iterator.hasNext()) {
            Map<String, Object> sourceAsMap = iterator.next().getSourceAsMap();
            for (String s : sourceAsMap.keySet()) {
                Object o = sourceAsMap.get(s);
                System.out.println("s = " + s + " ==== " + o.toString());
            }
//            System.out.println("documentFields = " + documentFields);
        }

        return "finish";
    }


}
