package cn.henry.dao.system;

import cn.henry.domain.system.User;
import org.apache.ibatis.annotations.Param;
import java.util.List;


public interface UserDao {

	//根据企业id查询全部
	List<User> findAll(String companyId);

	//根据id查询
    User findById(String userId);

	//根据id删除
	int delete(String userId);

	//保存
	int save(User user);

	//更新
	int update(User user);

	//根据邮箱查询
    User findByEmail(String email);

	User findByOpenId(String openId);

	void updatewx(User user);
}