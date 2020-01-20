package cn.henry.web.controller.system;

import cn.henry.domain.system.Module;
import cn.henry.domain.system.Role;
import cn.henry.service.system.ModuleService;
import cn.henry.service.system.RoleService;
import cn.henry.web.controller.BaseController;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/system/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private ModuleService moduleService;

    @RequestMapping(value = "/list",name = "分页查询角色列表")
    public String list(@RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "5") int size){
        PageInfo pageInfo = roleService.findAll(page, size, companyId);
        request.setAttribute("page",pageInfo);
        return "system/role/role-list";
    }

    @RequestMapping(value = "/toAdd",name = "转发到新增角色页面")
    public String toAdd(){
        return "system/role/role-add";
    }

    @RequestMapping(value = "edit",name = "编辑角色页面")
    public String edit(Role role){
        role.setCompanyId(companyId);
        role.setCompanyName(companyName);
        if (StringUtil.isEmpty(role.getId())){
            role.setId(UUID.randomUUID().toString());
            roleService.save(role);
        }else {
            roleService.update(role);
        }
        return "redirect:/system/role/list.do";
    }

    @RequestMapping(value = "toUpdate",name = "转发到编辑角色页面")
    public String toUpdate(String id){
        //回填
        Role role = roleService.findById(id);
        request.setAttribute("role",role);
        return "system/role/role-update";
    }

    @RequestMapping(value = "delete",name = "删除角色")
    public String delete(String id){
        roleService.delete(id);
        return "redirect:/system/role/list.do";
    }

    @RequestMapping(value = "roleModule",name = "跳转到角色模块分配界面")
    public String roleModule(String roleid){
        //回填角色信息
        Role role = roleService.findById(roleid);
        request.setAttribute("role",role);
        return "system/role/role-module";
    }

    @RequestMapping(value = "initModuleData",name = "返回角色模块z-tree")
    public @ResponseBody List<Map> initModuleData(String id){
        //查询所有模块
        List<Module> moduleList = moduleService.findAll();
        //根据id查询角色所拥有的模块
        List<Module> roleModule = moduleService.findByRoleId(id);
        //构建一个list集合返回前端页面
        List<Map> list = new ArrayList<Map>();
        for (Module module : moduleList) {
            Map map = new HashMap();
            map.put("id",module.getId());
            map.put("pId",module.getParentId());
            map.put("name",module.getName());

            if (roleModule.contains(module)){
                map.put("checked",true);
            }
            list.add(map);
        }
        return list;
    }

    @RequestMapping(value = "updateRoleModule",name = "更新角色模块权限")
    public String updateRoleModule(String roleId,String moduleIds){
        //roleId:当前角色id
        //moduleIds：选中的全部模块id
        moduleService.updateRoleModule(roleId,moduleIds);
        return "redirect:/system/role/list.do";
    }
}
