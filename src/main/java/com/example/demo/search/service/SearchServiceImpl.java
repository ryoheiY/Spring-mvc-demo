package com.example.demo.search.service;

import com.example.demo.search.entity.ResultEntity;
import com.example.demo.search.model.SearchCondition;
import com.example.demo.search.model.SearchResult;
import com.example.demo.search.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    SearchRepository searchRepository;

    @Autowired
    public SearchServiceImpl(SearchRepository repository) {
        this.searchRepository = repository;
    }

    /**
     * 初期表示時の値をセットアップする。
     *
     * @param condition condition
     */
    public void init(SearchCondition condition) {
        condition.setNameField("Yamashita");
        condition.setAgeFrom(10);
        condition.setAgeTo(20);
    }

    /**
     * 条件をもとにResultテーブルを検索する。
     *
     * @param condition condition
     * @return 検索結果のリスト
     */
    public List<SearchResult> search(SearchCondition condition) {
        List<ResultEntity> entities = searchRepository.findByDynamicCondition(condition);
        List<SearchResult> searchResults = new ArrayList<>();
        for (ResultEntity entity : entities) {
            SearchResult searchResult = new SearchResult();
            dataTransfer(searchResult, entity);
            searchResults.add(searchResult);
        }
        return searchResults;
    }

    /**
     * searchResult　Entityから必要データだけを取り出す。
     *
     * @param searchResult 検索結果　画面データクラス
     * @param resultEntity 　Entity
     */
    private void dataTransfer(SearchResult searchResult, ResultEntity resultEntity) {
        searchResult.setName(resultEntity.getName());
        searchResult.setAge(resultEntity.getAge());
        searchResult.setEmail(resultEntity.getEmail());
    }
}
