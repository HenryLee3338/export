package cn.henry.service.system.impl;

import cn.henry.dao.system.UserDao;
import cn.henry.domain.system.User;
import cn.henry.service.system.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public void save(User user) {
        userDao.save(user);
    }

    public User findById(String id) {
        return userDao.findById(id);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public void delete(String id) {
        userDao.delete(id);
    }

    public PageInfo findAll(int page, int size, String companyId) {
        PageHelper.startPage(page,size);
        List<User> userList = userDao.findAll(companyId);
        return new PageInfo(userList);
    }

    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    public User findByOpenId(String openId) {
        return userDao.findByOpenId(openId);
    }

    public void updatewx(User user) {
        userDao.updatewx(user);
    }
}
