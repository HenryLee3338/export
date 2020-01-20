package cn.henry.dao.cargo;

import cn.henry.domain.cargo.Contract;
import cn.henry.domain.cargo.ContractExample;
import cn.henry.domain.vo.ContractProductVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ContractDao {

	//删除(delete(String id))
    int deleteByPrimaryKey(String id);

	//保存(save(实体类))
    int insertSelective(Contract record);

	//条件查询(findAll)
    List<Contract> selectByExample(ContractExample example);

	//id查询(findById(String id))
    Contract selectByPrimaryKey(String id);

	//更新(update(实体类))
    int updateByPrimaryKeySelective(Contract record);

    List<ContractProductVo> findByInputDate(@Param("inputDate") String inputDate, @Param("companyId") String companyId);
}