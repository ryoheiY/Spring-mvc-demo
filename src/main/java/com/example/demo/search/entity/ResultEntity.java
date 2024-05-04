package com.example.demo.search.entity;

import lombok.Data;

/**
 * 検索処理で使用するresultテーブルのEntity
 */
@Data
public class ResultEntity {
    private String name;
    private String email;
    private Integer age;
}
