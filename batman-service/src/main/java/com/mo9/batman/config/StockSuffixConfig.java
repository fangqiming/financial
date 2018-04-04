package com.mo9.batman.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 15:16 2018/4/3
 * @Modified By:
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "system")
public class StockSuffixConfig {
    private String debtUrlSuffix;
    private String benefitUrlSuffix;
    private String cashUrlSuffix;
    private String bankUrlSuffix;
    private String growUrlSuffix;
    private String payUrlSuffix;
    private String operateUrlSuffix;
    private String eachUrlSuffix;
}
