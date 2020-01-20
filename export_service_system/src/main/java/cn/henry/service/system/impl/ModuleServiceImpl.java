package cn.henry.service.system.impl;

import cn.henry.dao.system.ModuleDao;
import cn.henry.domain.system.Module;
import cn.henry.domain.system.User;
import cn.henry.service.system.ModuleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private ModuleDao moduleDao;

    public Module findById(String moduleId) {
        return moduleDao.findById(moduleId);
    }

    public void delete(String moduleId) {
        moduleDao.delete(moduleId);
    }

    public void save(Module module) {
        moduleDao.save(module);
    }

    public void update(Module module) {
        moduleDao.update(module);
    }

    public List<Module> findAll() {
        return moduleDao.findAll();
    }

    public PageInfo findAll(int page, int size) {
        PageHelper.startPage(page,size);
        List<Module> list = moduleDao.findAll();
        return new PageInfo(list);
    }

    public List<Module> findByRoleId(String id) {
        return moduleDao.findByRoleId(id);
    }

    public void updateRoleModule(String roleId, String moduleIds) {
        //清空该角色id在中间表中的所有数据
        moduleDao.deleteById(roleId);
        //取出moduleIds中的所有模块id，存入到中间表中
        String[] moduleIdArray = moduleIds.split(",");
        for (String moduleId : moduleIdArray) {
            moduleDao.insertRoleModule(roleId,moduleId);
        }
    }

    public List<Module> findByUser(User user) {
        //1.如果user.degree==0，是SaaS平台管理员
        if (user.getDegree() == 0){
            //查找module.belong==0的模块
           return moduleDao.findByBelong(0);

        //2.如果user.degree==1,是企业管理员
        }else if (user.getDegree() == 1){
            return moduleDao.findByBelong(1);
        }
        //3.其他用户,查找对应角色的对应模块
        return moduleDao.findByUserId(user.getId());
    }
}
