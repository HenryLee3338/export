package cn.henry.dao.system;

import cn.henry.domain.system.Dept;

import java.util.List;

public interface DeptDao {

    //查询该企业下的所有部门
    List<Dept> findAll(String companyId);

    //根据id查询部门信息
    Dept findById(String id);

    //保存部门信息
    void save(Dept dept);

    //更新部门信息
    void update(Dept dept);

    //通过id删除部门信息
    void delete(String id);
}
