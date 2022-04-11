package com.choi.marvel.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration: 설정정보를 스프링 실행시 등록해줌.
@Configuration // <- 이것만 주석처리하면 로그인 했는지 안했지 체크를 안함.
public class WebConfig implements WebMvcConfigurer {
    // 로그인 여부에 따른 접속가능 페이지 구분
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor()) // 만든 LoginCheckIntercepter 클래스 내용을 넘김
                .order(1) // 해당 인터셉터가 적용되는 순서
                .addPathPatterns("/**") // 해당 프로젝트의 모든 주소에 대해 인터셉터를 적용
                .excludePathPatterns("/", "/character", "/member/save","/member/emailCheck", "/member/login","/member/logout", "/css/**", "/experiment/**", "/board/"); // 그중에 이 주소는 제외
    }

}
