package cn.henry.service.cargo;

import cn.henry.dao.cargo.*;
import cn.henry.domain.cargo.*;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private InvoiceDao invoiceDao;
    @Autowired
    private PackingDao packingDao;
    @Autowired
    private ExportDao exportDao;
    @Autowired
    private ContractDao contractDao;
    @Autowired
    private ShippingDao shippingDao;
    public PageInfo findAll(InvoiceExample example, int page, int size) {
         PageHelper.startPage(page, size);
        List<Invoice> list = invoiceDao.selectByExample(example);
        return new PageInfo(list);
    }

    public void delete(String invoiceId) {
        invoiceDao.deleteByPrimaryKey(invoiceId);
    }

    public void update(Invoice invoice) {

        invoiceDao.updateByPrimaryKeySelective(invoice);
    }

    public Invoice findById(String invoiceId) {
        return invoiceDao.selectByPrimaryKey(invoiceId);

    }

    public void save(Invoice invoice) {
       //id是委托单的id,需要更根据这个id查询到装箱(装箱\委托\发票三个ID是同一个)
        Packing packing = packingDao.selectByPrimaryKey(invoice.getInvoiceId());
        //为invoice赋值
        invoice.setInvoiceId(invoice.getInvoiceId());

        //提单号由新建改为从委托中取
        //通过invoiceId查询委托类
        Shipping shipping = shippingDao.selectByPrimaryKey(invoice.getInvoiceId());
        invoice.setTradeTerms(shipping.getLcNo());//提单号

        invoice.setState(0);
        invoice.setCreateBy(packing.getCreateBy());//创建人
        invoice.setCreateDept(packing.getCreateDept());//创建部门
        invoice.setCreateTime(packing.getCreateTime());//创建部门
        //设置目前的实收金额为0
        invoice.setRealMoney(0d);
        //设置应收金额
        double totalMoney=0d;
        String[] exprotIds = packing.getExportIds().split(",");
        for (String exprotId : exprotIds) {
            //根据包运单Exportid查询报运单
            Export export = exportDao.selectByPrimaryKey(exprotId);
            //再根据报运单里面的合同单ids查询合同,根据id查询合同单
            String[] contractIds = export.getContractIds().split(",");
            for (String contractId : contractIds) {
                //在根据id查询合同单
                Contract contract = contractDao.selectByPrimaryKey(contractId);
                totalMoney+=contract.getTotalAmount();
            }
        }
        invoice.setTotalMoney(totalMoney);
        invoice.setSyMoney(totalMoney);
        invoiceDao.insert(invoice);
    }
}
