package cn.henry.service.cargo;

import cn.henry.domain.cargo.Finance;
import cn.henry.domain.cargo.FinanceExample;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface FinanceService {

    //查询所有财务单
    PageInfo findAll(FinanceExample example, int page, int size);

    //通过id查询
    Finance findById(String id);

    //新增财务单
    void save(Finance finance);

    //修改财务单
    void update(Finance finance);

    //删除财务单
    void delete(String id);

    Finance findByInvoiceId(String invoiceId);
}
