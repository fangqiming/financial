package com.mo9.batman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author : xjding
 * @date :   2017-12-25 11:50
 */
@EntityScan(
        basePackageClasses = {BatmanApplication.class}
)
@EnableAsync
@EnableScheduling
@SpringBootApplication
public class BatmanApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BatmanApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(BatmanApplication.class, args);
    }

}
