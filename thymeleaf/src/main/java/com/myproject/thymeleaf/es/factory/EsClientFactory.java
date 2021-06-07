package com.myproject.thymeleaf.es.factory;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author zhanjianjian
 * @since 2021/5/14
 */
@Slf4j
@Component
public class EsClientFactory {

    private static String userName;
    private static String password;
    private static String hostname;
    private static Integer port;

    @Value(value = "${es.username}")
    public void setUserName(String userName) {
        EsClientFactory.userName = userName;
    }

    @Value(value = "${es.password}")
    public void setPassword(String password) {
        EsClientFactory.password = password;
    }

    @Value(value = "${es.hostname}")
    public void setHostName(String hostname) {
        EsClientFactory.hostname = hostname;
    }

    @Value(value = "${es.port}")
    public void setPort(Integer port) {
        EsClientFactory.port = port;
    }

    public EsClientFactory() {
    }

    public volatile static RestHighLevelClient client;

    public static RestHighLevelClient getEsClient() {
        if (client == null) {
            synchronized (EsClientFactory.class) {
                if (client == null) {
                    CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
                    credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(userName, password));
                    RestClientBuilder clientBuilder = RestClient.builder(new HttpHost(hostname, port))
                            .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                                @Override
                                public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpAsyncClientBuilder) {
                                    return httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                                }
                            });
                    client = new RestHighLevelClient(clientBuilder);
                }
            }
        }
        return client;
    }

}
