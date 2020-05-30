package org.springboot.samples.es.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springboot.samples.es.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ElasticSearchRepository {
    @Autowired
    private RestHighLevelClient restHighLevelClient;
    @Value("${elastic-search-index}")
    private String index;
    @Autowired
    private ObjectMapper objectMapper;

    public void indexRequest(User user) throws Exception {
        IndexRequest indexRequest = new IndexRequest(index)
                .id("" + user.getId())
                .type("doc")
                .source(objectMapper.writeValueAsString(user), XContentType.JSON);
        restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
    }


}
