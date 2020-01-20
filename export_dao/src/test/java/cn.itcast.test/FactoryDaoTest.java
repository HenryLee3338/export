package cn.itcast.test;

import cn.henry.domain.cargo.Factory;
import cn.henry.dao.cargo.FactoryDao;
import cn.henry.domain.cargo.FactoryExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/applicationContext-*.xml")
public class FactoryDaoTest {
    @Autowired
    private FactoryDao factoryDao;

    @Test
    public void insertTest(){
        Factory factory = new Factory();
        factory.setId(UUID.randomUUID().toString());
        factory.setCreateTime(new Date());
        factory.setUpdateTime(new Date());
        factory.setFactoryName("新明会所3");
        factoryDao.insertSelective(factory);
    }

    @Test
    public void updateTest(){
        Factory factory = new Factory();
        factory.setId("f2eea025-da53-4cb8-a336-75225eac0d50");
        factory.setUpdateTime(new Date());
        factory.setFactoryName("新明会所4");
        factoryDao.updateByPrimaryKeySelective(factory);
    }

    @Test
    public void selectTest(){
        FactoryExample example = new FactoryExample();
        FactoryExample.Criteria criteria = example.createCriteria();
        criteria.andFactoryNameLike("%新明会所%");
        criteria.andIdEqualTo("751e0492-899e-41a8-ba98-8ddd9ce8e4d4");
        example.setOrderByClause("factory_name desc");
        List<Factory> factories = factoryDao.selectByExample(example);
        for (Factory factory : factories) {
            System.out.println(factory);
        }
    }

    @Test
    public void deleteTest(){
        factoryDao.deleteByPrimaryKey("f2eea025-da53-4cb8-a336-75225eac0d50");
    }
}
