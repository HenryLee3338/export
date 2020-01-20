package cn.henry.web.controller.system;


import cn.henry.domain.system.Module;
import cn.henry.service.system.ModuleService;
import cn.henry.web.controller.BaseController;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/system/module")
public class ModuleController extends BaseController {
    @Autowired
    private ModuleService moduleService;

    @RequestMapping(value = "/list",name = "分页查询模块列表")
    public String list(@RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "5") int size){
        PageInfo pageInfo = moduleService.findAll(page, size);
        request.setAttribute("page",pageInfo);
        return "system/module/module-list";
    }

    @RequestMapping(value = "toAdd",name = "跳转新增页面")
    public String toAdd(){
        //查询所有模块并回填到下拉列表
        List<Module> list = moduleService.findAll();
        request.setAttribute("menus",list);
        return "system/module/module-add";
    }

    @RequestMapping(value = "edit",name = "新增页面")
    public String edit(Module module){
        if (StringUtil.isEmpty(module.getId())){
            module.setId(UUID.randomUUID().toString());
            moduleService.save(module);
        }else {
            moduleService.update(module);
        }
        return "redirect:/system/module/list.do";
    }

    @RequestMapping(value = "toUpdate",name = "跳转编辑页面")
    public String toUpdate(String id){
        //查询所有模块并回填到下拉列表
        List<Module> list = moduleService.findAll();
        request.setAttribute("menus",list);
        //查询模块信息并回填
        Module module = moduleService.findById(id);
        request.setAttribute("module",module);
        return "system/module/module-update";
    }

    @RequestMapping(value = "delete",name = "删除模块")
    public String delete(String id){
        moduleService.delete(id);
        return "redirect:/system/module/list.do";
    }
}
