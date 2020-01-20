package cn.henry.service.system;

import cn.henry.domain.vo.FactoryAmountVo;

import java.util.List;

public interface FactoryAmountService {
    //按厂家分组查询
    List<FactoryAmountVo> groupByFactoryId();
}
