package cn.henry.service.cargo;

import cn.henry.dao.cargo.ExportDao;
import cn.henry.dao.cargo.PackingDao;
import cn.henry.domain.cargo.Export;
import cn.henry.domain.cargo.Packing;
import cn.henry.domain.cargo.PackingExample;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PackingServiceImpl implements PackingService {

    @Autowired
    private PackingDao packingDao;

    @Autowired
    private ExportDao exportDao;

    public PageInfo findAll(PackingExample example, int page, int size) {
        PageHelper.startPage(page, size);
        List<Packing> list = packingDao.selectByExample(example);
        return new PageInfo(list);
    }

    public List<Packing> findAll(PackingExample example) {
//        PageHelper.startPage(page, size);
        List<Packing> list = packingDao.selectByExample(example);
        return list;
    }

    public Packing findById(String id) {
        return packingDao.selectByPrimaryKey(id);
    }
    public void save(Packing packing) {
        packingDao.insert(packing);
    }
    /*public void save(Packing packing) {
        //将packing中的export的状态全都设置为3
        String exportIds = packing.getExportIds();
        System.out.println("====================");
        System.out.println(exportIds.toString());
        System.out.println("====================");
        String[] exportIdArray = exportIds.split(",");
        for (String exportId : exportIdArray) {
            Export export = exportDao.selectByPrimaryKey(exportId);
            System.out.println("====================");
            System.out.println(export);
            System.out.println(exportId);
            System.out.println("====================");
            if ("2".equals(exportId)){
                continue;
            }
            export.setState(3);
            exportDao.updateByPrimaryKeySelective(export);
        }
        //将packing存到packing的数据库当中
        packingDao.insertSelective(packing);
    }*/

    public void update(Packing packing) {
        packingDao.updateByPrimaryKeySelective(packing);
    }

    public void delete(String id) {
        packingDao.deleteByPrimaryKey(id);
    }
}
