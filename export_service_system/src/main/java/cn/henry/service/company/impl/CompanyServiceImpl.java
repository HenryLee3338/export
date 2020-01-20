package cn.henry.service.company.impl;

import cn.henry.dao.company.CompanyDao;
import cn.henry.domain.company.Company;
import cn.henry.service.company.CompanyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyDao companyDao;

    //查询全部
    public List<Company> findAll() {
        return companyDao.findAll();
    }
    //分页查询
    public PageInfo findByPageHelper(int page, int size) {
        System.out.println("测试");
        //1、调用PageHelper.startPage方法
        PageHelper.startPage(page, size);
        //2、紧跟着第一个查询会被分页
        List<Company> list = companyDao.findAll();
        //3、返回PageInfo
        return new PageInfo(list);
    }


    public void save(Company company) {
        companyDao.save(company);
    }

    public Company findById(String id) {
        return companyDao.findById(id);
    }

    public void update(Company company) {
        companyDao.update(company);
    }

    public void delete(String id) {
        companyDao.delete(id);
    }


}
