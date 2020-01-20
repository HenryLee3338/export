package cn.henry.service.system.impl;

import cn.henry.dao.system.RoleDao;
import cn.henry.domain.system.Role;
import cn.henry.service.system.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    public Role findById(String id) {
        return roleDao.findById(id);
    }

    //查询全部
    public List<Role> findAll(String companyId) {
        return roleDao.findAll(companyId);
    }

    //分页查询全部
    public PageInfo findAll(int page, int size,String companyId) {
        PageHelper.startPage(page,size);
        List<Role> list = roleDao.findAll(companyId);
        return new  PageInfo(list);
    }


    public void delete(String id) {
        roleDao.delete(id);
    }

    public void save(Role role) {
        roleDao.save(role);
    }

    public void update(Role role) {
        roleDao.update(role);
    }

    public List findRoleByUserId(String id) {
        return roleDao.findRoleByUserId(id);
    }

    public void changeRole(String userid, String roleIds) {
        //根据userid清除user-role中间表
        roleDao.deleteByUserId(userid);
        //将新的角色写入
        String[] roleIdArray = roleIds.split(",");
        for (String roleId : roleIdArray) {
            roleDao.insertUserRole(userid,roleId);
        }
    }
}
