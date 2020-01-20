package cn.henry.service.cargo;

import cn.henry.domain.cargo.Contract;
import cn.henry.domain.cargo.ContractExample;
import cn.henry.domain.vo.ContractProductVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ContractService{
    //分页查询所有
    PageInfo findAll(ContractExample example, int page, int size);

    //通过id查询合同
    Contract findById(String id);

    //保存合同信息
    void save(Contract contract);

    //更新合同信息
    void update(Contract contract);

    //通过id删除合同
    void delete(String id);

    List<ContractProductVo> findByInputDate(String inputDate, String companyId);
}
