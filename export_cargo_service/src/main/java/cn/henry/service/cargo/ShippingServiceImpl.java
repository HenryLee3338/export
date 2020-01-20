package cn.henry.service.cargo;

import cn.henry.dao.cargo.ExportDao;
import cn.henry.dao.cargo.PackingDao;
import cn.henry.dao.cargo.ShippingDao;
import cn.henry.domain.cargo.*;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ShippingServiceImpl implements ShippingService {

    @Autowired
    private ExportDao exportDao;

    @Autowired
    private PackingDao packingDao;

    @Autowired
    private ShippingDao shippingDao;

    public PageInfo findAll(ShippingExample example, int page, int size) {
        PageHelper.startPage(page, size);
        List<Shipping> list = shippingDao.selectByExample(example);
        return new PageInfo(list);
    }

    public Shipping findById(String id) {
        return shippingDao.selectByPrimaryKey(id);
    }

    public void save(Shipping shipping) {
        //1.根据id查询到packing再查询到export
        String id = shipping.getShippingOrderId();
        Packing packing = packingDao.selectByPrimaryKey(id);
        packing.setState(2);
        packingDao.updateByPrimaryKeySelective(packing);
        //2.将id所对应的packing的所有的export的state改为4,已委托
        String exportIds = packing.getExportIds();
        String[] exportIdArray = exportIds.split(",");
        for (String exportId : exportIdArray) {
            Export export = exportDao.selectByPrimaryKey(exportId);
            export.setState(4);
            exportDao.updateByPrimaryKeySelective(export);
        }

        //3..将shipping存入到数据库当中
        shippingDao.insertSelective(shipping);
    }

    public void update(Shipping shipping) {
        shippingDao.updateByPrimaryKeySelective(shipping);
    }

    public void delete(String id) {
        shippingDao.deleteByPrimaryKey(id);
    }
}
