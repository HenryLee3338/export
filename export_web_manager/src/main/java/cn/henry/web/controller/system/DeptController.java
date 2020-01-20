package cn.henry.web.controller.system;

import cn.henry.domain.system.Dept;
import cn.henry.service.system.DeptService;
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
@RequestMapping("/system/dept")
public class DeptController extends BaseController {
    @Autowired
    private DeptService deptService;

    @RequestMapping(value = "list",name = "分页查询部门列表")
    public String list(@RequestParam(defaultValue = "1")int page,
                       @RequestParam(defaultValue = "5")int size){
        PageInfo pageInfo = deptService.findAll(page,size,companyId);
        request.setAttribute("page",pageInfo);
        return "system/dept/dept-list";
    }

    @RequestMapping(value = "toAdd",name = "跳转新建部门页面")
    public String toAdd(){
        List<Dept> list = deptService.findAll(companyId);
        request.setAttribute("deptList", list);
        return "system/dept/dept-add";
    }

    @RequestMapping(value = "edit",name = "编辑部门")
    public String edit(Dept dept){
        dept.setCompanyId(companyId);
        dept.setCompanyName(companyName);

        if (StringUtil.isEmpty(dept.getId())){
            //新建
            dept.setId(UUID.randomUUID().toString());
            deptService.save(dept);
        }else {
            //编辑
            deptService.update(dept);
        }
        return "redirect:/system/dept/list.do";
    }

    @RequestMapping(value = "/toUpdate", name = "跳转部门编辑页面")
    public String toUpdate(String id){
        //1、通过id查询部门实体类
        Dept dept = deptService.findById(id);
        request.setAttribute("dept", dept);
        //2、查询所有部门列表
        List<Dept> list = deptService.findAll(companyId);
        request.setAttribute("deptList", list);

        return "system/dept/dept-update";
    }

    @RequestMapping(value = "/delete", name = "删除部门信息")
    public String delete(String id){
        deptService.delete(id);
        return "redirect:/system/dept/list.do";
    }
}
