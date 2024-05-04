package com.example.demo.search.repository;

import com.example.demo.search.entity.ResultEntity;
import com.example.demo.search.model.SearchCondition;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

@MybatisTest
@TestPropertySource(locations = "classpath:test.properties")
public class SearchRepositoryTest {

    private final SearchRepository searchRepository;

    @Autowired
    public SearchRepositoryTest(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    @Test
    public void findByName() {
        SearchCondition condition = new SearchCondition();
        condition.setNameField("yamanaka");
        List<ResultEntity> results = searchRepository.findByDynamicCondition(condition);

        for(ResultEntity resultEntity : results){
            assert resultEntity.getName().contains("yamanaka");
        }

    }
}
