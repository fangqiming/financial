package com.mo9.batman.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author:qmfang
 * @Description: 基本对象
 * @Date:Created in 10:53 2018/3/26
 * @Modified By:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MetaData {

    private String title;
    private String value;
}
