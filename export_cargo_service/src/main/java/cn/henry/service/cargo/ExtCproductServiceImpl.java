package cn.henry.service.cargo;

import cn.henry.dao.cargo.ContractDao;
import cn.henry.dao.cargo.ExtCproductDao;
import cn.henry.domain.cargo.Contract;
import cn.henry.domain.cargo.ExtCproduct;
import cn.henry.domain.cargo.ExtCproductExample;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ExtCproductServiceImpl implements ExtCproductService {
    @Autowired
    private ExtCproductDao extCproductDao;
    @Autowired
    private ContractDao contractDao;

    public PageInfo findAll(ExtCproductExample example, int page, int size) {
        PageHelper.startPage(page,size);
        List<ExtCproduct> list = extCproductDao.selectByExample(example);
        return new PageInfo(list);
    }

    public ExtCproduct findById(String id) {
        return extCproductDao.selectByPrimaryKey(id);
    }

    public void save(ExtCproduct extCproduct) {
        //1.传入的参数是附件实体类
        //2.通过传入参数计算附件的金额
        double amount = 0.0d;
        if (extCproduct.getCnumber() != null && extCproduct.getPrice() != null){
            amount = extCproduct.getCnumber() * extCproduct.getPrice();
        }
        //3.设置附件的金额
        extCproduct.setAmount(amount);
        //4.通过extCproduct得到contractId得到合同实体类
        Contract contract = contractDao.selectByPrimaryKey(extCproduct.getContractId());
        if (contract!=null){
            //5.计算合同的附件数量（合同本身的附件数量 + 本次附件的数量）
            //6.设置合同附件的数量
            if (contract.getExtNum()!=null){
                contract.setExtNum(contract.getExtNum() + extCproduct.getCnumber());
            }else {
                contract.setExtNum(extCproduct.getCnumber());
            }
            //7.计算合同的总金额（合同本身的总金额 + 本附件的金额）
            //8.设置合同的总金额
            if (contract.getTotalAmount()!=null){
                contract.setTotalAmount(contract.getTotalAmount() + amount);
            }else {
                contract.setTotalAmount(amount);
            }
            //9.写入附件数据库
            extCproductDao.insertSelective(extCproduct);
            //10.更新合同到数据库
            contractDao.updateByPrimaryKeySelective(contract);
        }

    }

    public void update(ExtCproduct extCproduct) {
        //1.传入的参数是附件实体类
        //2.通过传入参数计算附件的金额
        double amount = 0.0d;
        if (extCproduct.getCnumber() != null && extCproduct.getPrice() != null){
            amount = extCproduct.getCnumber() * extCproduct.getPrice();
        }
        //3.设置附件的金额
        extCproduct.setAmount(amount);
        //4.通过cxtCproduct得到contractId得到contract实体类
        Contract contract = contractDao.selectByPrimaryKey(extCproduct.getContractId());
        //5.通过extCproduct得到id去数据库查询得到原来的附件实体类
        ExtCproduct oldExtCproduct = extCproductDao.selectByPrimaryKey(extCproduct.getId());
        //6.计算合同的附件数量(减去旧的，加上新的)
        //7.设置合同的附件数量
        contract.setExtNum(contract.getExtNum() - oldExtCproduct.getCnumber() + extCproduct.getCnumber());
        //8.计算合同的总金额(减去旧的附件金额，加上新的附件金额)
        //9.设置合同的总金额
        contract.setTotalAmount(contract.getTotalAmount() - oldExtCproduct.getAmount() + extCproduct.getAmount());
        //10.更新附件到数据库
        extCproductDao.updateByPrimaryKeySelective(extCproduct);
        //11.更新合同到数据库
        contractDao.updateByPrimaryKeySelective(contract);
    }

    public void delete(String id) {
        //1.通过id查询附件实体类
        ExtCproduct extCproduct = extCproductDao.selectByPrimaryKey(id);
        //2.通过附件实体类得到contractId得到contract实体类
        Contract contract = contractDao.selectByPrimaryKey(extCproduct.getContractId());
        //3.减去合同中删除掉的附件数量
        //4.设置合同中的附件数量
        contract.setExtNum(contract.getExtNum() - extCproduct.getCnumber());
        //5.减去合同中附件的金额
        //6.设置合同中的总金额
        contract.setTotalAmount(contract.getTotalAmount() - extCproduct.getAmount());
        //7.更新合同到数据库
        contractDao.updateByPrimaryKeySelective(contract);
        //8.删除附件
        extCproductDao.deleteByPrimaryKey(id);
    }
}
