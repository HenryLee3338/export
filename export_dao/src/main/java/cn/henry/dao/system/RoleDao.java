package cn.henry.dao.system;

import cn.henry.domain.system.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


public interface RoleDao {

    //根据id查询
    Role findById(String id);

    //查询全部用户
    List<Role> findAll(String companyId);

	//根据id删除
    int delete(String id);

	//添加
    int save(Role role);

	//更新
    int update(Role role);

    List findRoleByUserId(String id);

    void deleteByUserId(String userid);

    void insertUserRole(@Param("userid") String userid, @Param("roleId") String roleId);
}