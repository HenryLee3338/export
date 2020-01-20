package cn.henry.dao.system;

import cn.henry.domain.system.Module;
import cn.henry.domain.system.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 */
public interface ModuleDao {

    //根据id查询
    Module findById(String moduleId);

    //根据id删除
    int delete(String moduleId);

    //添加用户
    int save(Module module);

    //更新用户
    int update(Module module);

    //查询全部
    List<Module> findAll();

    //通过roleId查询模块
    List<Module> findByRoleId(String id);

    //根据id删除在中间表中的所有数据
    void deleteById(String roleId);

    //在中间表中添加数据
    void insertRoleModule(@Param("roleId") String roleId, @Param("moduleId") String moduleId);

    //根据belong找到对应的模块
    List<Module> findByBelong(int belong);

    //通过用户id找到对应角色的对应模块
    List<Module> findByUserId(String id);
}