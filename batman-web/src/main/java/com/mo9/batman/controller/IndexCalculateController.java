package com.mo9.batman.controller;

import com.mo9.batman.dao.entity.vo.ContinuousVo;
import com.mo9.batman.dao.entity.vo.SingleVo;
import com.mo9.batman.dao.mapper.IndexRemarkMapper;
import com.mo9.batman.dao.mapper.OriginalEachMapper;
import com.mo9.batman.dao.model.IndexRemark;
import com.mo9.batman.entity.vo.IndexConditionVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.groupingBy;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 17:09 2018/4/17
 * @Modified By:
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class IndexCalculateController {

    @Resource
    private OriginalEachMapper originalEachMapper;

    @Resource
    private IndexRemarkMapper indexRemarkMapper;

    @PostMapping(value = "getCode")
    public Object getIndex(@RequestBody IndexConditionVo indexConditionVo) {
        List<String> result = new ArrayList<>();
        List<List<String>> singleCode = new ArrayList<>();
        if (!Objects.isNull(indexConditionVo) && !Objects.isNull(indexConditionVo.getSingleVos())
                && !Objects.isNull(indexConditionVo.getContinuousVos())) {
            for (SingleVo singleVo : indexConditionVo.getSingleVos()) {
                singleCode.add(originalEachMapper.findSingleIndex(singleVo));
                System.out.println(singleCode.get(0));
            }
            for (ContinuousVo continuousVo : indexConditionVo.getContinuousVos()) {
                singleCode.add(originalEachMapper.findContinuousIndex(continuousVo.setGap()));
                System.out.println(singleCode.get(1));
            }
            //取交集
            result = singleCode.get(0);
            for (List<String> list : singleCode) {
                result.retainAll(list);
            }
        }
        return result;
    }

    /**
     * 根据SQL语句生成查询查询语句
     *
     * @param table
     * @return
     */
    @PostMapping(value = "getData")
    public Object getData(@RequestBody String table) {
        String result = "";
        String line[] = table.split("\\r\\n");
        String tableName = line[0].split("`")[1].split("`")[0];
        for (String str : line) {
            if (str.startsWith("  `") && !str.contains("AUTO_INCREMENT")) {
                String name = str.split("`")[1].split("`")[0];
                String remark = str.split("'")[1].split("'")[0];
                result += "INSERT INTO index_remark(name,remark,tableName) VALUES ('" + name + "', '" + remark + "', '" + tableName + "');\r\n";
            }
        }
        return result;

    }

    /**
     * 获取所有的含义解释
     *
     * @return
     */
    @GetMapping(value = "getIndexRemark")
    public Object getIndexRemark() {
        List<IndexRemark> remarks = indexRemarkMapper.findAll();
        System.out.println(remarks);
        Map<String, List<IndexRemark>> result = remarks.stream().collect(groupingBy(IndexRemark::getTableName));
        return result;
    }
}
