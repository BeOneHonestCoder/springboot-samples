package org.springboot.samples.es.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springboot.samples.es.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public void deleteRequest(User user) throws Exception {
        DeleteRequest deleteRequest = new DeleteRequest(index)
                .id("" + user.getId())
                .type("doc");
        restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
    }

    public void updateRequest(User user) throws Exception {
        UpdateRequest updateRequest = new UpdateRequest(index, "doc", "" + user.getId())
                .doc(objectMapper.writeValueAsString(user), XContentType.JSON);
        restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
    }

    public User getRequest(User user) throws Exception {
        GetRequest getRequest = new GetRequest(index)
                .id("" + user.getId());
        GetResponse response = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        return readValue(response.getSourceAsString());
    }

    public List<User> searchRequest(User user) throws Exception {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        SearchRequest searchRequest = new SearchRequest(index)
                .source(searchSourceBuilder);
        SearchHits searchHits = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT).getHits();
        List<User> users = Arrays.stream(searchHits.getHits()).map(SearchHit::getSourceAsString).map(this::readValue).collect(Collectors.toList());
        return users;
    }

    private User readValue(String payload) {
        try {
            return objectMapper.readValue(payload, User.class);
        } catch (IOException e) {
            throw new RuntimeException("");
        }
    }

}
