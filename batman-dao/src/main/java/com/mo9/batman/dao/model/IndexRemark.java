package com.mo9.batman.dao.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 17:39 2018/4/17
 * @Modified By:
 */
@Data
@TableName("index_remark")
public class IndexRemark {

    @TableId
    private Long id;

    private String name;

    private String remark;

    private String tableName;
}
