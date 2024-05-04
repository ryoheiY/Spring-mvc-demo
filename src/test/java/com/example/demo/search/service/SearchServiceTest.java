package com.example.demo.search.service;

import com.example.demo.search.model.SearchCondition;
import com.example.demo.search.model.SearchResult;
import com.example.demo.search.repository.SearchRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
@Transactional
public class SearchServiceTest {

    SearchRepository repository;

    SearchService searchService;

    @Autowired
    private SearchServiceTest(SearchRepository repository){
        this.repository = repository;
        this.searchService = new SearchServiceImpl(repository);
    }

    @Test
    public void searchTestByNameOnly(){
        SearchCondition condition = new SearchCondition();
        condition.setNameField("yamanaka");
        List<SearchResult> resultList = this.searchService.search(condition);
        System.out.println(resultList);
        assert resultList.size() == 5;
    }

    @Test
    public void searchTestByNameAndAgeFrom(){
        SearchCondition condition = new SearchCondition();
        condition.setNameField("yamanaka");
        condition.setAgeFrom(29);
        List<SearchResult> resultList = this.searchService.search(condition);
        System.out.println(resultList);
        assert resultList.size() == 3;
    }

    @Test
    public void searchTestByAgeTo(){
        SearchCondition condition = new SearchCondition();
        condition.setAgeTo(29);
        List<SearchResult> resultList = this.searchService.search(condition);
        System.out.println(resultList);
        assert resultList.size() == 4;
    }
}
