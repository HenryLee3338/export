package cn.henry.web.controller.cargo;

import cn.henry.domain.cargo.Finance;
import cn.henry.domain.cargo.FinanceExample;
import cn.henry.domain.cargo.InvoiceExample;
import cn.henry.service.cargo.FinanceService;
import cn.henry.service.cargo.InvoiceService;
import cn.henry.web.controller.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
@RequestMapping("/cargo/finance")
public class FinanceController extends BaseController {
    @Reference
    private InvoiceService invoiceService;
    @Reference
    private FinanceService financeService;
    //在财务管理里面显示发票列表
    @RequestMapping("/list")
    public String list(@RequestParam(defaultValue = "1") int page,
                        @RequestParam(defaultValue = "10") int size){
        InvoiceExample example=new InvoiceExample();
        InvoiceExample.Criteria criteria = example.createCriteria();
        //criteria.andStateEqualTo(0);
        criteria.andCompanyIdEqualTo(companyId);
        PageInfo pageInfo=invoiceService.findAll(example,page,size);
        request.setAttribute("page",pageInfo);
        return "cargo/finance/finance-list";
    }

    //展示财务列表
    @RequestMapping(value = "/list1")
    public String list1(@RequestParam(defaultValue = "1") int page,
                        @RequestParam(defaultValue = "10") int size){
        FinanceExample example = new FinanceExample();
        FinanceExample.Criteria criteria = example.createCriteria();
        criteria.andCompanyIdEqualTo("1");
//        example.setOrderByClause( " create_time desc " );
        PageInfo pageInfo = financeService.findAll(example, page, size);
        request.setAttribute("page",pageInfo);
        //System.out.println(pageInfo);
        return "cargo/finance/finance-list1";
    }


    //编辑财务列表
    @RequestMapping(value = "/edit")
    public String edit(Finance finance){
        finance.setCompanyId(companyId);
        finance.setCompanyName(companyName);
        finance.setCreateTime(new Date());
        financeService.update(finance);
        return "redirect:/cargo/finance/list.do";
    }

    //删除财务列表
    @RequestMapping(value = "/delete")
    public String delete(String id){
        financeService.delete(id);
        return "redirect:/cargo/finance/list.do";
    }

    //提交财务列表
    @RequestMapping(value = "/submit")
    public String submit(String id){
        Finance finance = financeService.findById(id);
        finance.setState(1);
        financeService.update(finance);
        return "redirect:/cargo/finance/list1.do";
    }

    @RequestMapping(value = "cancel")
    public String cancel(String id){
        Finance finance = financeService.findById(id);
        finance.setState(0);
        financeService.update(finance);
        return "redirect:/cargo/finance/list1.do";
    }
}
