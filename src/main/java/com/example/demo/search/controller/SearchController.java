package com.example.demo.search.controller;

import com.example.demo.search.model.SearchCondition;
import com.example.demo.search.model.SearchResult;
import com.example.demo.search.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.saml2.provider.service.authentication.Saml2AuthenticatedPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SearchController {

    Logger logger = LoggerFactory.getLogger(SearchController.class);

    SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    /**
     * 初期表示想定
     * @param principal 認証ユーザ情報の入れ物
     * @param modelAndView モデルとviewの入れ物
     * @return
     */
    @GetMapping("/search")
    public ModelAndView initPage(@AuthenticationPrincipal Saml2AuthenticatedPrincipal principal, ModelAndView modelAndView) {
        SearchCondition condition = new SearchCondition();
        searchService.init(condition);
        //Tokenから情報を取得
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        //view, model
        modelAndView.setViewName("index");
        modelAndView.addObject("searchCondition", condition);
        return modelAndView;
    }

    @PostMapping("/search")
    public ModelAndView executeSearch(@ModelAttribute @Validated SearchCondition condition, BindingResult bindingResult, ModelAndView modelAndView) {
        modelAndView.setViewName("index");
        modelAndView.addObject("searchCondition", condition);

        if(bindingResult.hasErrors()){
            logger.info("Error!");
            logger.info(bindingResult.getAllErrors().toString());
            modelAndView.addObject("errorMsg", "error!");
            return modelAndView;
        }

        List<SearchResult> results = searchService.search(condition);
        modelAndView.addObject("results", results);
        return modelAndView;
    }
}
