package com.mo9.batman.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author:qmfang
 * @Description: 财务指标信息
 * @Date:Created in 10:24 2018/3/26
 * @Modified By:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FinancialIndexBO {
    private List<String> title;
    private List<List<String>> report;
    private List<List<String>> simple;
    private List<List<String>> year;
}
