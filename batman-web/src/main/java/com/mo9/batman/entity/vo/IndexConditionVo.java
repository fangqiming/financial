package com.mo9.batman.entity.vo;

import com.mo9.batman.dao.entity.vo.ContinuousVo;
import com.mo9.batman.dao.entity.vo.SingleVo;
import lombok.Data;

import java.util.List;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 17:12 2018/4/17
 * @Modified By:
 */
@Data
public class IndexConditionVo {

    /**
     * 单个指标信息查询
     */
    private List<SingleVo> singleVos;

    /**
     * 连续指标信息查询
     */
    private List<ContinuousVo> continuousVos;
}
