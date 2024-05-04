package com.example.demo.search.repository;

import com.example.demo.search.entity.ResultEntity;
import com.example.demo.search.model.SearchCondition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@Mapper
public interface SearchRepository {
    /**
     * 検索条件を元にresultテーブルからデータを取得。
     *
     * @param condition 動的検索条件
     * @return 検索結果レコードのリスト
     */
    @SelectProvider(type = SearchRepositoryProvider.class, method = "find")
    List<ResultEntity> findByDynamicCondition(SearchCondition condition);

    /**
     * 動的にSQLを生成するためのProvider
     */
    class SearchRepositoryProvider {
        /**
         * findByDynamicConditionの動的SQL生成Ï
         *
         * @param condition 検索条件クラス
         * @return sql
         */
        public String find(SearchCondition condition) {
            return new SQL() {{
                SELECT("name", "email", "age");
                FROM("result");
                if (!StringUtils.isEmpty(condition.getNameField())) {
                    WHERE("name LIKE CONCAT('%',#{nameField},'%')");
                }
                if (condition.getAgeFrom() != null) {
                    AND();
                    WHERE("age >= #{ageFrom}");
                }
                if (condition.getAgeTo() != null) {
                    AND();
                    WHERE("age <= #{ageTo}");
                }
            }}.toString();
        }
    }


}
