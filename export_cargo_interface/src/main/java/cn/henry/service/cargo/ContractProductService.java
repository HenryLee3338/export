package cn.henry.service.cargo;

import cn.henry.domain.cargo.ContractProduct;
import cn.henry.domain.cargo.ContractProductExample;
import cn.henry.domain.vo.FactoryAmountVo;
import com.github.pagehelper.PageInfo;

public interface ContractProductService {

    PageInfo findAll(ContractProductExample example,int page,int size);

    //通过id查询合同
    ContractProduct findById(String id);

    //保存合同信息
    void save(ContractProduct contractProduct);

    //更新合同信息
    void update(ContractProduct contractProduct);

    //通过id删除合同
    void delete(String id);

}