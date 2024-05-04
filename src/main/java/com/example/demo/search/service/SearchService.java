package com.example.demo.search.service;

import com.example.demo.search.model.SearchCondition;
import com.example.demo.search.model.SearchResult;

import java.util.List;

public interface SearchService {

    void init(SearchCondition condition);

    List<SearchResult> search(SearchCondition condition);
}
