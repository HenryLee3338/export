package cn.henry.service.system.impl;

import cn.henry.dao.cargo.ContractProductDao;
import cn.henry.domain.vo.FactoryAmountVo;
import cn.henry.service.system.FactoryAmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactoryAmountServiceImpl implements FactoryAmountService {

    @Autowired
    private ContractProductDao contractProductDao;

    public List<FactoryAmountVo> groupByFactoryId() {
        return contractProductDao.groupByFactoryId();
    }
}
