package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /*
     * 認可設定とログインの設定を定義
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);

        http
                .authorizeHttpRequests(authz -> authz
                        // URLごとの認可設定
                        // 認証したユーザのみがアクセス可能な画面として、"/top"を用意しておく
                        //.requestMatchers(mvcMatcherBuilder.pattern("/top*")).hasAuthority("ROLE_USER")
                        .anyRequest().authenticated()
                ).saml2Login(saml2Login -> saml2Login
                        // SAML 2.0プロトコルの認証設定をカスタマイズする場合は、ここにCustomizerを指定します
                        .defaultSuccessUrl("/search") // 認証成功後のデフォルトのリダイレクト先を設定する場合など)
                ).saml2Metadata(
                        Customizer.withDefaults()
                ).csrf(
                        // CSRFキー有効化設定（デフォルト：有効）
                        Customizer.withDefaults()
                );
        return http.build();
    }
}