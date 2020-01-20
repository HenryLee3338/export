package cn.henry.web.controller.factory;

import cn.henry.domain.cargo.Factory;
import cn.henry.domain.cargo.FactoryExample;
import cn.henry.domain.system.Dept;
import cn.henry.service.cargo.FactoryService;
import cn.henry.web.controller.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/factory")
public class FactoryController extends BaseController {
    @Reference
    private FactoryService factoryService;
	
    @RequestMapping(value = "/list")
    public String list(@RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "5") int size){

        FactoryExample example = new FactoryExample();
        FactoryExample.Criteria criteria = example.createCriteria();
        criteria.andCtypeBetween("货物","附件" );
        PageInfo pageInfo = factoryService.findAllfenye(example, page, size);
        //5. 将contractId放入到request域当中
        request.setAttribute("page",pageInfo);
        //6. 跳转货物列表页面
        return "factory/factory-list";


    }

    @RequestMapping(value = "/toAdd", name = "跳转厂家新建页面")
    public String toAdd(){


        return "factory/factory-add";
    }


    @RequestMapping(value = "/edit", name = "保存厂家信息")
    public String edit(Factory factory){

        if (StringUtil.isEmpty(factory.getId())){
            //新建
            factory.setId(UUID.randomUUID().toString());
            factory.setCreateTime(new Date());
            factoryService.save(factory);
        }else {
            //编辑
            factory.setUpdateTime(new Date());
            factoryService.update(factory);
        }
        return "redirect:/factory/list.do";
    }


    @RequestMapping(value = "/toUpdate", name = "跳转厂家编辑页面")
    public String toUpdate(String id){
        Factory factory=factoryService.findById(id);
       request.setAttribute("factory",factory );

        return  "factory/factory-update";
    }

    @RequestMapping(value = "/delete", name = "删除厂家信息")
    public String delete(String id){
        factoryService.delete(id);
        return "redirect:/factory/list.do";
    }

}

