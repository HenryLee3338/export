package cn.henry.service.system;

import cn.henry.domain.system.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {

    //保存企业信息
    void save(User user);

    //通过id查询企业信息
    User findById(String id);

    //更新企业信息
    void update(User user);

    //通过id进行删除
    void delete(String id);

    //通过PageHelper进行分页
    PageInfo findAll(int page, int size, String companyId);

    User findByEmail(String email);

    //查询用户的OpenId
    User findByOpenId(String openId);

    //更新微信User
    void updatewx(User user);
}