package cn.henry.web.controller.systemcode;

import cn.henry.domain.cargo.Factory;
import cn.henry.domain.cargo.FactoryExample;
import cn.henry.domain.cargo.Huohao;
import cn.henry.domain.cargo.HuohaoExample;
import cn.henry.service.cargo.FactoryService;
import cn.henry.service.cargo.HuoHaoService;
import cn.henry.web.controller.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/systemcode")
public class HuoHaoController extends BaseController {

    @Reference
    private HuoHaoService huoHaoService;

    @Reference
    private FactoryService factoryService;

    @RequestMapping(value = "/list")
    public String list(@RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "10") int size){
        HuohaoExample example = new HuohaoExample();
        HuohaoExample.Criteria criteria = example.createCriteria();
        criteria.andCompanyIdEqualTo(companyId);
        PageInfo pageInfo = huoHaoService.findAll(example, page, size);
        request.setAttribute("page",pageInfo);
        return "systemcode/systemcode-list";
    }

    @RequestMapping(value = "/toAdd")
    public String toAdd(){
        FactoryExample factoryExample = new FactoryExample();
        List<Factory> factoryList = factoryService.findAll(factoryExample);
        //4.将货物厂家信息放入request域中
        request.setAttribute("factoryList", factoryList);
        return "systemcode/systemcode-add";
    }

    @RequestMapping(value = "/edit")
    public String edit(Huohao huohao){
        if (StringUtil.isEmpty(huohao.getId())){
            //新建
            huohao.setId(UUID.randomUUID().toString());
            huohao.setCompanyId(companyId);
            huohao.setCompanyName(companyName);
            String factoryName = huohao.getFactoryName();
            FactoryExample factoryExample = new FactoryExample();
            FactoryExample.Criteria criteria = factoryExample.createCriteria();
            criteria.andFactoryNameEqualTo(factoryName);
            List<Factory> list = factoryService.findAll(factoryExample);
            for (Factory factory : list) {
                String id = factory.getId();
                huohao.setFactoryId(id);
            }
            huoHaoService.save(huohao);
        }else {
            //修改
            huoHaoService.update(huohao);
        }
        return "redirect:/systemcode/list.do";
    }

    @RequestMapping(value = "/toUpdate")
    public String toUpdate(String id){
        Huohao huohao = huoHaoService.findById(id);
        request.setAttribute("huohao",huohao);
        return "systemcode/systemcode-update";
    }

    @RequestMapping(value = "/delete")
    public String delete(String id){
        huoHaoService.delete(id);
        return "redirect:/systemcode/list.do";
    }
}
