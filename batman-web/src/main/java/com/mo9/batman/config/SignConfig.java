package com.mo9.batman.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author : xjding
 * @date :   2018-01-10 14:43
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "system.sign")
public class SignConfig {

    private String secretKey;   //签名密钥
    private Long limitSeconds;  //签名有效期

}
