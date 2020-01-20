package cn.henry.service.system.impl;

import cn.henry.dao.system.DeptDao;
import cn.henry.domain.system.Dept;
import cn.henry.service.system.DeptService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService{

    @Autowired
    private DeptDao deptDao;

    public PageInfo findAll(int page, int size, String companyId) {
        PageHelper.startPage(page, size);
        List<Dept> list = deptDao.findAll(companyId);
        return new PageInfo(list);
    }

    public Dept findById(String id) {
        return deptDao.findById(id);
    }

    public void save(Dept dept) {
        deptDao.save(dept);
    }

    public void update(Dept dept) {
        deptDao.update(dept);
    }

    public void delete(String id) {
        deptDao.delete(id);
    }

    public List<Dept> findAll(String companyId) {
        return deptDao.findAll(companyId);
    }
}
