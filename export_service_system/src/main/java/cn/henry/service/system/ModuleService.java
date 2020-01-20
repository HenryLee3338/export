package cn.henry.service.system;

import cn.henry.domain.system.Module;
import cn.henry.domain.system.User;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ModuleService {
    //根据id查询
    Module findById(String moduleId);

    //根据id删除
    void delete(String moduleId);

    //添加用户
    void save(Module module);

    //更新用户
    void update(Module module);

    //查询全部
    List<Module> findAll();

    //分页查询全部
    PageInfo findAll(int page,int size);

    //通过角色id查找模块
    List<Module> findByRoleId(String id);

    //更新角色模块中间表
    void updateRoleModule(String roleId, String moduleIds);

    //通过用户查找模块列表
    List<Module> findByUser(User user);
}
