package cn.henry.dao.cargo;

import cn.henry.domain.cargo.ContractProduct;
import cn.henry.domain.cargo.ContractProductExample;
import cn.henry.domain.vo.FactoryAmountVo;

import java.util.List;

public interface ContractProductDao {

	//删除
    int deleteByPrimaryKey(String id);

	//保存
    int insertSelective(ContractProduct record);

	//条件查询
    List<ContractProduct> selectByExample(ContractProductExample example);

	//id查询
    ContractProduct selectByPrimaryKey(String id);

	//更新
    int updateByPrimaryKeySelective(ContractProduct record);

    //按厂家分组查询
    List<FactoryAmountVo> groupByFactoryId();
}