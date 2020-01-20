package cn.henry.service.system;

import cn.henry.domain.system.Role;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    //根据id查询
    Role findById(String id);

    //查询全部用户
    List<Role> findAll(String companyId);

    //分页查询全部用户
    public PageInfo findAll(int page,int size,String companyId);

    //根据id删除
    void delete(String id);

    //添加
    void save(Role role);

    //更新
    void update(Role role);

    List findRoleByUserId(String id);

    void changeRole(String userid, String roleIds);
}
