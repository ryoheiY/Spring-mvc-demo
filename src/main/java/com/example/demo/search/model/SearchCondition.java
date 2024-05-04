package com.example.demo.search.model;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SearchCondition {
    @NotNull(message = "yabai")
    String nameField;

    Integer ageFrom;

    Integer ageTo;

    @AssertTrue(message = "From, Toの大小関係が不正です。")
    public boolean isFromToValidate(){
        if(ageFrom != null && ageTo != null){
            return this.ageFrom <= this.ageTo;
        }
        return true;
    }

    @AssertTrue(message = "検索条件を入力してください。")
    public boolean isAllInputCheck(){
        return (this.nameField != null && !this.nameField.isEmpty())
                || this.ageFrom != null || this.ageTo != null;
    }
}
