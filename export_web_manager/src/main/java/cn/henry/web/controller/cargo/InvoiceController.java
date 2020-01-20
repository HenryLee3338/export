package cn.henry.web.controller.cargo;


import cn.henry.domain.cargo.*;
import cn.henry.service.cargo.FinanceService;
import cn.henry.service.cargo.InvoiceService;
import cn.henry.service.cargo.ShippingService;
import cn.henry.web.controller.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
@RequestMapping("/cargo/invoice")
public class InvoiceController extends BaseController {

    @Reference
    private InvoiceService invoiceService;
    @Reference
    private ShippingService shippingService;
    @Reference
    private FinanceService financeService;


    @RequestMapping("/list")
    public String list(@RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "10") int size){
        ShippingExample example=new ShippingExample();
        ShippingExample.Criteria criteria = example.createCriteria();
        criteria.andCompanyIdEqualTo(companyId);
        //到时候再商量看显示到底需要什么条件
        PageInfo pageInfo=shippingService.findAll(example,page,size);
        request.setAttribute("page",pageInfo);
        return "cargo/invoice/invoice-list";
    }


    //开发票功能
    @RequestMapping("/invoice")
    public String toInvoice(String id){
        //1.修改委托类的状态
        Shipping shipping = shippingService.findById(id);
        shipping.setState(2);
        shippingService.update(shipping);
        //2.新建一个发票实例
        Invoice invoice=new Invoice();
        invoice.setCompanyId(companyId);
        invoice.setInvoiceId(id);
        invoiceService.save(invoice);
        return "redirect:/cargo/invoice/list.do";
    }

    //删除发票功能



    //取消发票功能
//    @RequestMapping(value = "cancel")
//    public String cancel(String id){
//        //1.修改委托类的状态
//        Shipping shipping = shippingService.findById(id);
//        shipping.setState(1);
//        shippingService.update(shipping);
//        //2.删除发票实例
//        invoiceService.delete(id);
//        return "redirect:/cargo/invoice/list.do";
//    }



    //提交剩余金额的功能
    @RequestMapping("/change")
    public String change(Double realMoney,String invoiceId){
        Invoice invoice = invoiceService.findById(invoiceId);
        if (invoice.getRealMoney()!=null){
            invoice.setRealMoney(invoice.getRealMoney()+realMoney);
        }else{
            invoice.setRealMoney(realMoney);
        }
        invoice.setSyMoney(invoice.getTotalMoney()-invoice.getRealMoney());
        if (invoice.getSyMoney()!=0){
            invoice.setBlNo("还有"+invoice.getSyMoney()+"待收余款");
            invoice.setState(1);
            invoiceService.update(invoice);
            return "redirect:/cargo/finance/list.do";
        }else{
            invoice.setBlNo("余款结清");
            invoice.setState(2);
            invoiceService.update(invoice);
            return "redirect:/cargo/finance/list.do";
        }
    }

    @RequestMapping(value = "/submit")
    public String submit(String id){
        Invoice invoice = invoiceService.findById(id);
        invoice.setBlNo("余款结清");
        invoice.setState(3);//将invoice的状态改为已上报
        invoiceService.update(invoice);//更新invoice
        //创建finance对象
        Finance finance = new Finance();
        finance.setCompanyId(companyId);
        finance.setCompanyName(companyName);
        finance.setInvoiceId(invoice.getInvoiceId());
        finance.setRealMoney(invoice.getRealMoney());
        finance.setCreateBy(invoice.getCreateBy());
        finance.setCreateDept(invoice.getCreateDept());
        financeService.save(finance);
        return "redirect:/cargo/finance/list1.do";
    }
}
