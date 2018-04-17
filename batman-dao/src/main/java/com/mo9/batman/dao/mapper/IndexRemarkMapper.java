package com.mo9.batman.dao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mo9.batman.dao.model.IndexRemark;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 18:14 2018/4/17
 * @Modified By:
 */
public interface IndexRemarkMapper extends BaseMapper<IndexRemark> {

    @Select("select * from index_remark")
    List<IndexRemark> findAll();
}
