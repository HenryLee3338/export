package cn.henry.service.cargo;

import cn.henry.dao.cargo.FinanceDao;
import cn.henry.domain.cargo.Finance;
import cn.henry.domain.cargo.FinanceExample;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class FinanceServiceImpl implements FinanceService {

    @Autowired
    private FinanceDao financeDao;

    public PageInfo findAll(FinanceExample example, int page, int size) {
        PageHelper.startPage(page,size);
        List<Finance> list = financeDao.selectByExample(example);
        return new PageInfo(list);
    }

    public Finance findById(String id) {
        return financeDao.selectByPrimaryKey(id);
    }

    public void save(Finance finance) {
        finance.setFinanceId(UUID.randomUUID().toString());
        finance.setState(0);
        //finance.setCreateBy(user.getId());
        //finance.setCreateDept(user.getDeptId());
        finance.setCreateTime(new Date());
        financeDao.insertSelective(finance);
    }

    public void update(Finance finance) {
        financeDao.updateByPrimaryKeySelective(finance);
    }

    public void delete(String id) {
        financeDao.deleteByPrimaryKey(id);
    }

    //根据发票id查询财务,判断有没有
    public Finance findByInvoiceId(String invoiceId) {
        return financeDao.findByInvoiceId(invoiceId);
    }


}
