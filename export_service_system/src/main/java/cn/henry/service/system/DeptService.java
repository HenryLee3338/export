package cn.henry.service.system;

import cn.henry.domain.system.Dept;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface DeptService {

    //分页查询该企业下的所有部门
    PageInfo findAll(int page, int size, String companyId);

    //根据id查询部门信息
    Dept findById(String id);

    //保存部门信息
    void save(Dept dept);

    //更新部门信息
    void update(Dept dept);

    //通过id删除部门信息
    void delete(String id);

    //查询所有部门，不分页
    List<Dept> findAll(String companyId);
}
