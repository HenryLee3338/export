package cn.henry.service.cargo;

import cn.henry.dao.cargo.ContractDao;
import cn.henry.domain.cargo.Contract;
import cn.henry.domain.cargo.ContractExample;
import cn.henry.domain.vo.ContractProductVo;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService{

    @Autowired
    private ContractDao contractDao;

    public PageInfo findAll(ContractExample example, int page, int size) {
        PageHelper.startPage(page, size);
        List<Contract> list = contractDao.selectByExample(example);
        return new PageInfo(list);
    }

    public Contract findById(String id) {
        return contractDao.selectByPrimaryKey(id);
    }

    public void save(Contract contract) {
        contractDao.insertSelective(contract);
    }

    public void update(Contract contract) {
        contractDao.updateByPrimaryKeySelective(contract);
    }

    public void delete(String id) {
        contractDao.deleteByPrimaryKey(id);
    }

    public List<ContractProductVo> findByInputDate(String inputDate, String companyId) {
        return contractDao.findByInputDate(inputDate,companyId);
    }
}
