package org.springboot.samples.es.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ElasticSearchPropertiesConfig.class)
public class ElasticSearchConfig {

    @Bean
    public RestHighLevelClient restHighLevelClient(ElasticSearchPropertiesConfig config){
        return new RestHighLevelClient(RestClient.builder(new HttpHost(config.getHost(), config.getPort(), config.getProtocol())));
    }

}
