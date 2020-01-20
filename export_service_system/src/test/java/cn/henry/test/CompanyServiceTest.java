package cn.henry.test;


import cn.henry.domain.company.Company;
import cn.henry.service.company.CompanyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/spring/applicationContext-*.xml")
public class CompanyServiceTest {
    @Autowired
    private CompanyService companyService;

    @Test
    public void test(){
        List<Company> companies = companyService.findAll();
        System.out.println(companies);
    }
}
