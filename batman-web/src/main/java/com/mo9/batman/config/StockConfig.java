package com.mo9.batman.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 15:50 2018/3/27
 * @Modified By:
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "system")
public class StockConfig {

    private List<String> stock;

    private String path;

    private Integer fileNum;

    private List<String> titleName;

    private List<String> commitName;

    private List<String> isvarchar;

    private List<String> isData;

    private String tableName;

}
