package cn.henry.test;

import cn.henry.dao.company.CompanyDao;
import cn.henry.domain.company.Company;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/applicationContext-*.xml")
public class CompanyDaoTest {

    @Autowired
    private CompanyDao companyDao;


    @Test
    public void findAllTest(){
        List<Company> list = companyDao.findAll();
        System.out.println(list.toString());
    }
}