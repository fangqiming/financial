package com.mo9.batman.config;

import com.mo9.batman.constant.WebRoute;
import com.mo9.batman.filter.StreamFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Mvc配置
 * @author : xjding
 * @date :   2017-12-25 11:52
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(signInterceptor)
//                .addPathPatterns(WebRoute.API_PATH + "/**")
//                .excludePathPatterns("/error");
    }

    /**
     * 输入流包装转换
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBeanForStreamParse() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        StreamFilter streamFilter = new StreamFilter();
        registrationBean.setFilter(streamFilter);
        List<String> urlPatterns = new ArrayList<>();
        urlPatterns.add(WebRoute.API_PATH + "/*");
        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;
    }
}
