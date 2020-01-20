package cn.henry.web.controller.cargo;

import cn.henry.domain.cargo.*;
import cn.henry.domain.vo.ExportProductVo;
import cn.henry.domain.vo.ExportResult;
import cn.henry.domain.vo.ExportVo;
import cn.henry.service.cargo.ContractService;
import cn.henry.service.cargo.ExportProductService;
import cn.henry.service.cargo.ExportService;
import cn.henry.web.controller.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.apache.cxf.jaxrs.client.WebClient;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/cargo/export")
public class ExportController extends BaseController {

    @Reference
    private ContractService contractService;
    @Reference
    private ExportService exportService;
    @Reference
    private ExportProductService exportProductService;


//    ====================================================合同管理页面==================================================
//    ============================================在此页面展示contract表中state=1的内容=================================
    //合同管理页面，在此页面可以点击报运按钮
    @RequestMapping("contractList")
    public String contractList(@RequestParam(defaultValue = "1") int page,
                               @RequestParam(defaultValue = "10") int size){
        ContractExample example = new ContractExample();
        ContractExample.Criteria criteria = example.createCriteria();
        criteria.andStateEqualTo(1);//查询所有状态为已上报的合同
        criteria.andCompanyIdEqualTo(companyId);
        PageInfo pageInfo = contractService.findAll(example, page, size);
        request.setAttribute("page",pageInfo);
        return "cargo/export/export-contractList";
    }

    //点击报运按钮, 带着合同的id转发到export-toExport，新增export的页面
    @RequestMapping("toExport")
    public String toExport(String id){
        request.setAttribute("id",id);//将合同的id传给点击报运之后的页面
        return "cargo/export/export-toExport";
    }


//    ======================================================保存或者更新export表========================================
    @RequestMapping(value = "/edit")
    public String edit(Export export){
        if (StringUtil.isEmpty(export.getId())){//从合同页面新创建的export,export有id，是通过export-toExport发送过来的export
            export.setId(UUID.randomUUID().toString());
            export.setCompanyId(companyId);
            export.setCompanyName(companyName);
            export.setCreateTime(new Date());
            exportService.save(export);//将前端页面传过来的export存到export的数据库表中
        }else {
            exportService.update(export);
        }
        return "redirect:/cargo/export/list.do";
    }


//    =======================================================出口报运页面==============================================
//    ===================================在此页面展示export表中的所有内容，state=0,1,2==================================
    //出口页面，在此页面可以点击电子报运按钮
    @RequestMapping(value = "/list")
    public String list(@RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "10") int size){

        ExportExample exportExample = new ExportExample();
        ExportExample.Criteria criteria = exportExample.createCriteria();
        criteria.andCompanyIdEqualTo(companyId);
        exportExample.setOrderByClause("create_time desc");
        PageInfo pageInfo = exportService.findAll(exportExample, page, size);
        request.setAttribute("page", pageInfo);
        return "cargo/export/export-list";
    }
    //在出口报运页面点击编辑按钮
    @RequestMapping(value = "/toUpdate")
    public String toUpdate(String id){

        //1.通过id查找报运主表实体类
        Export export = exportService.findById(id);

        //2.查找报运商品列表
        ExportProductExample example = new ExportProductExample();
        ExportProductExample.Criteria criteria = example.createCriteria();
        criteria.andExportIdEqualTo(id);
        List<ExportProduct> list = exportProductService.findAll(example);

        //3.将export,eps放入request域当中
        request.setAttribute("export", export);
        request.setAttribute("eps", list);

        //4.跳转页面
        return "cargo/export/export-update";
    }

    //在出口报运页面点击提交按钮, 改变export.state=1, 已提交状态
    @RequestMapping(value = "/submit")
    public String submit(String id){
        Export export = exportService.findById(id);
        export.setState(1);
        exportService.update(export);
        return "redirect:/cargo/export/list.do";
    }

    //在出口报运页面点击提交按钮, 改变export.state=0, 草稿状态
    @RequestMapping(value = "/cancel")
    public String cancel(String id){
        Export export = exportService.findById(id);
        export.setState(0);
        exportService.update(export);
        return "redirect:/cargo/export/list.do";
    }


    //在出口报运页面点击删除按钮，改变包含的所有contract的状态为0，删除对应的export
    @RequestMapping(value = "/delete")
    public String delete(String id){
        //1.根据id查询export
        Export export = exportService.findById(id);
        //2.得到export中的所有contract
        String contractIds = export.getContractIds();
        String[] contractIdArray = contractIds.split(",");
        for (String contractId : contractIdArray) {
            Contract contract = contractService.findById(contractId);
            contract.setState(1);
            contractService.update(contract);
        }
        //3.根据删除该export
        exportService.delete(id);
        return "redirect:/cargo/export/list.do";
    }


    //在出口报运页面点击电子报运按钮，调用海关接口, 改变export.state=2, 已报运状态
    @RequestMapping(value = "/exportE")
    public String exportE(String id) {
        //1.创建ExportVo实体类
        ExportVo exportVo = new ExportVo();
        //2.通过id查询export实体类
        Export export = exportService.findById(id);
        //3.将export实体类中的信息复制到ExportVo实体类中
        BeanUtils.copyProperties(export, exportVo);
        exportVo.setExportId(id);
        //4.创建ExportProductVo所在的列表
        List<ExportProductVo> exportProductVos = new ArrayList();
        //5.通过id查询exportProduct列表
        ExportProductExample example = new ExportProductExample();
        ExportProductExample.Criteria criteria = example.createCriteria();
        criteria.andExportIdEqualTo(id);
        List<ExportProduct> products = exportProductService.findAll(example);
        //6.循环报运商品列表
        for (ExportProduct product : products) {
            ExportProductVo exportProductVo = new ExportProductVo();
            //7.给ExportProductVo赋值
            BeanUtils.copyProperties(product, exportProductVo);
            exportProductVo.setExportId(id);
            exportProductVo.setExportProductId(product.getId());
            exportProductVos.add(exportProductVo);
        }
        //8.给ExportVo.products赋值
        exportVo.setProducts(exportProductVos);

        //System.out.println(exportVo);

        //9.调用webservice接口
        WebClient wc = WebClient.create("http://localhost:8088/ws/export/user");
        wc.post(exportVo);

        wc = WebClient.create("http://localhost:8088/ws/export/user/" + id);
        ExportResult exportResult = wc.get(ExportResult.class);

        //System.out.println(exportResult);

        exportService.updateE(exportResult);

        return "redirect:/cargo/export/list.do";

    }
}
