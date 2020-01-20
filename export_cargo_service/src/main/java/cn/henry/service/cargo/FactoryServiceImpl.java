package cn.henry.service.cargo;

import cn.henry.dao.cargo.FactoryDao;
import cn.henry.domain.cargo.Factory;
import cn.henry.domain.cargo.FactoryExample;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class FactoryServiceImpl implements FactoryService{

    @Autowired
    private FactoryDao factoryDao;

    public List<Factory> findAll(FactoryExample example) {
        return factoryDao.selectByExample(example);
    }

    public PageInfo findAllfenye(FactoryExample example, int page, int size) {
        PageHelper.startPage(page, size);
        List<Factory> list = factoryDao.selectByExample(example);
        return new PageInfo(list);
    }

    public void save(Factory factory) {
        factoryDao.insertSelective(factory);
    }

    public void update(Factory factory) {
        factoryDao.updateByPrimaryKeySelective(factory);
    }

    public Factory findById(String id) {
        return factoryDao.selectByPrimaryKey(id);
    }

    public void delete(String id) {
        factoryDao.deleteByPrimaryKey(id);
    }

//    public Factory findByName(String factoryName) {
//        return factoryDao.findByName(factoryName);
//    }
}
