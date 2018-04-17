package com.mo9.batman.dao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mo9.batman.dao.entity.vo.ContinuousVo;
import com.mo9.batman.dao.entity.vo.SingleVo;
import com.mo9.batman.dao.model.OriginalEach;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 13:53 2018/4/17
 * @Modified By:
 */
public interface OriginalEachMapper extends BaseMapper<OriginalEach> {

    /**
     * 用户获取某一个指标值在某个指定的范围内
     *
     * @param singleVo
     * @return
     */
    List<String> findSingleIndex(@Param("singleVo") SingleVo singleVo);

    /**
     * 用来获取某个指标在某个时间范围内均在指定的范围内
     *
     * @param continuousVo
     * @return
     */
    List<String> findContinuousIndex(@Param("continuousVo") ContinuousVo continuousVo);

}
