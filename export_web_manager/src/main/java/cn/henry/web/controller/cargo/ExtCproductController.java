package cn.henry.web.controller.cargo;

import cn.henry.common.utils.UploadUtil;
import cn.henry.domain.cargo.*;
import cn.henry.service.cargo.ExtCproductService;
import cn.henry.service.cargo.FactoryService;
import cn.henry.service.cargo.HuoHaoService;
import cn.henry.web.controller.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/cargo/extCproduct")
public class ExtCproductController extends BaseController {
    @Reference
    private ExtCproductService extCproductService;

    @Reference
    private FactoryService factoryService;

    @Reference
    private HuoHaoService huoHaoService;

    @RequestMapping("/list")
    public String list(@RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "10") int size,
                        String contractId,
                        String contractProductId){
        //1.合同id
        request.setAttribute("contractId",contractId);
        //2.货物id
        request.setAttribute("contractProductId",contractProductId);
        //3.厂家的列表，ctype=附件
        FactoryExample factoryExample = new FactoryExample();
        FactoryExample.Criteria factoryCriteria = factoryExample.createCriteria();
        factoryCriteria.andCtypeEqualTo("附件");
        List<Factory> factoryList = factoryService.findAll(factoryExample);

        //4.放如request
        request.setAttribute("factoryList",factoryList);

        //5.extCproduct
        ExtCproductExample example = new ExtCproductExample();
        ExtCproductExample.Criteria criteria = example.createCriteria();
        criteria.andContractProductIdEqualTo(contractProductId);
        PageInfo pageInfo = extCproductService.findAll(example, page, size);
        request.setAttribute("page", pageInfo);
        return "cargo/extc/extc-list";
    }

    @RequestMapping(value = "/edit")
    public String edit(ExtCproduct extCproduct, MultipartFile productPhoto) throws IOException {
        //System.out.println("========================"+extCproduct.toString());

        extCproduct.setCompanyId(companyId);
        extCproduct.setCompanyName(companyName);

        if (productPhoto!=null){
            String upload = new UploadUtil().upload(productPhoto.getBytes());
            extCproduct.setProductImage(upload);
        }

        if (StringUtil.isEmpty(extCproduct.getId())){
            extCproduct.setId(UUID.randomUUID().toString());
            extCproductService.save(extCproduct);
        }else {
            extCproductService.update(extCproduct);
        }

        return "redirect:/cargo/extCproduct/list.do?contractId="+
                extCproduct.getContractId()+"&contractProductId="+extCproduct.getContractProductId();
    }


    @RequestMapping(value = "/toUpdate")
    public String toUpdate(String id, String contractId, String contractProductId){
        ExtCproduct extCproduct = extCproductService.findById(id);
        request.setAttribute("extCproduct", extCproduct);
        request.setAttribute("contractId", contractId);
        request.setAttribute("contractProductId", contractProductId);

        FactoryExample factoryExample = new FactoryExample();
        FactoryExample.Criteria factoryCriteria = factoryExample.createCriteria();
        factoryCriteria.andCtypeEqualTo("附件");
        List<Factory> factoryList = factoryService.findAll(factoryExample);
        request.setAttribute("factoryList", factoryList);

        return "cargo/extc/extc-update";
    }


    @RequestMapping(value = "/delete")
    public String delete(String id, String contractId, String contractProductId){
        extCproductService.delete(id);
        return "redirect:/cargo/extCproduct/list.do?contractId="+
                contractId+"&contractProductId="+contractProductId;
    }

    @RequestMapping(value = "findHuohao")
    @ResponseBody
    public List<String> findHuohao(String factoryName) {
        System.out.println(factoryName);
        HuohaoExample example = new HuohaoExample();
        HuohaoExample.Criteria criteria = example.createCriteria();
        criteria.andFactoryNameEqualTo(factoryName);
        List<Huohao> huohaoList = huoHaoService.findByFactoryName(example);
        ArrayList<String> list = new ArrayList<String>();
        for (Huohao huohao : huohaoList) {
            String huohaoName = huohao.getHuohaoName();
            list.add(huohaoName);
        }
        return list;
    }

}
