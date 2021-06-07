package com.myproject.thymeleaf.es.util;

import com.myproject.thymeleaf.es.bean.vo.EsTestVo;
import com.myproject.thymeleaf.es.factory.EsClientFactory;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.ActiveShardCount;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhanjianjian
 * @since 2021/6/7
 */
@Slf4j
public class EsUtils {

    public static final String MAPPING_TYPE = "type";
    public static final String MAPPING_TYPE_KEYWORD = "keyword";
    public static final String MAPPING_TYPE_INTEGER = "integer";
    public static final String MAPPING_TYPE_LONG = "long";
    public static final String MAPPING_TYPE_DOUBLE = "double";
    public static final String MAPPING_TYPE_BOOLEAN = "boolean";
    public static final String MAPPING_TYPE_OBJECT = "object";


    /**
     * 创建索引
     *
     * @param indexName 索引名
     * @param alias     别名
     * @param clazz     待映射的类
     */
    public static void createdIndex(String indexName, String alias, Class clazz) {
        Map<String, Object> mappingMap = createdMapping(clazz);

        CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
        createIndexRequest.alias(new Alias(alias));
        createIndexRequest.settings(Settings.builder()
                //分片数
                .put("index.number_of_shards", 3)
                //备份数)
                .put("index.number_of_replicas", 2));

        createIndexRequest.mapping("doc", mappingMap);
        createIndexRequest.masterNodeTimeout(TimeValue.timeValueMinutes(2));
        createIndexRequest.waitForActiveShards(ActiveShardCount.DEFAULT);
        RestHighLevelClient client = EsClientFactory.getEsClient();
        boolean acknowledged = false;
        try {
            CreateIndexResponse createIndexResponse = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
            acknowledged = createIndexResponse.isAcknowledged();
            log.info("acknowledged = " + acknowledged);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据class类型创建字段映射关系
     *
     * @param clazz
     * @return
     */
    public static Map<String, Object> createdMapping(Class clazz) {
        // 暂时不判断索引是否存在
        Map<String, Object> mappingMap = new HashMap<>();
        mappingMap.put("dynamic", "false");
        Map<String, Object> propertiesMap = new HashMap<>();

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Map<String, String> fieldTypeMap = new HashMap<>(16);
            if (field.getType().equals(String.class) || field.getType().equals(List.class)) {
                fieldTypeMap.put(MAPPING_TYPE, MAPPING_TYPE_KEYWORD);
            } else if (field.getType().equals(Integer.class)) {
                fieldTypeMap.put(MAPPING_TYPE, MAPPING_TYPE_INTEGER);
            } else if (field.getType().equals(Date.class)) {
                fieldTypeMap.put(MAPPING_TYPE, MAPPING_TYPE_LONG);
            }
            propertiesMap.put(field.getName(), fieldTypeMap);
        }
        mappingMap.put("properties", propertiesMap);
        return mappingMap;
    }

    /**
     * 判断索引是否存在
     *
     * @param indexName 索引名
     * @return
     */
    public static Boolean existIndex(String indexName) {
        GetIndexRequest request = new GetIndexRequest();
        request.indices(indexName).humanReadable(true);
        boolean exists = false;
        try {
            exists = EsClientFactory.getEsClient().indices().exists(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return exists;
    }

    /**
     * 删除索引
     *
     * @param indexName 索引名
     * @return
     */
    public static Boolean deletedIndex(String indexName) {
        DeleteIndexRequest request = new DeleteIndexRequest();
        request.indices(indexName);
        boolean acknowledged = false;
        try {
            AcknowledgedResponse delete = EsClientFactory.getEsClient().indices().delete(request, RequestOptions.DEFAULT);
            acknowledged = delete.isAcknowledged();
            log.info("acknowledged = " + acknowledged);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return acknowledged;
    }

    /**
     * 创建文档
     *
     * @param indexName  索引名称
     * @param documentId 文档id
     * @param esJson     文档内容
     */
    public static void createdDocument(String indexName, String documentId, String esJson) {
        Boolean existIndex = existIndex(indexName);
        if (!existIndex) {
            createdIndex(indexName, "alias", EsTestVo.class);
        }
        IndexRequest indexRequest = new IndexRequest();
        indexRequest.index(indexName).id(documentId).type("doc").source(esJson, XContentType.JSON).opType(DocWriteRequest.OpType.CREATE);
        try {
            IndexResponse index = EsClientFactory.getEsClient().index(indexRequest, RequestOptions.DEFAULT);
            log.info("index = " + index);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
