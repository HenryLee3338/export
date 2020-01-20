package cn.henry.web.controller.cargo;

import cn.henry.common.utils.MailUtil;
import cn.henry.domain.cargo.Packing;
import cn.henry.domain.cargo.PackingExample;
import cn.henry.domain.cargo.Shipping;
import cn.henry.domain.cargo.ShippingExample;
import cn.henry.service.cargo.PackingService;
import cn.henry.service.cargo.ShippingService;
import cn.henry.web.controller.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/cargo/shipping")
public class ShippingController extends BaseController {

    @Reference
    private ShippingService shippingService;

    @Reference
    private PackingService packingService;

    //展示的列表是装箱的列表
    @RequestMapping(value = "/list")
    public String list(@RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "10") int size){
        PackingExample example = new PackingExample();
        PackingExample.Criteria criteria = example.createCriteria();
        criteria.andCompanyIdEqualTo(companyId);
        PageInfo pageInfo = packingService.findAll(example, page, size);
        request.setAttribute("page",pageInfo);
        return "cargo/shipping/shipping-list";
    }

    //携带id转发到委托表单页面
    @RequestMapping("toShip")
    public String toShip(String id){
        request.setAttribute("id",id);
        return "cargo/shipping/shipping-toShip";
    }

    //提交委托表单，生成委托表对象，存入到数据库当中
    @RequestMapping(value = "edit")
    public String edit(Shipping shipping) throws Exception {
        //1.接收shipping对象,设置属性
//        shipping.setShippingOrderId();
        shipping.setState(1);//state=1, 在发票管理当中显示为草稿
        shipping.setCreateTime(new Date());
        shipping.setCompanyId(user.getCompanyId());
        shipping.setCompanyName(user.getCompanyName());
        shipping.setLcNo((String) UUID.randomUUID().toString().subSequence(0,7));//设置提单号

        System.out.println("========================================================");
        System.out.println(shipping);
        System.out.println("========================================================");
        //2.将shipping存入到数据库当中,同时修改packing的状态和export的状态
        shippingService.save(shipping);
        //3.发送邮件

        //确定两天后的日期
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        calendar.add(Calendar.DATE, 2);
        String two_days_after = sdf.format(calendar.getTime());

        String to = shipping.getNotifyParty();
        String subject = "提货单";
        String content = "     尊敬的" + shipping.getConsignee() +
                        "您好,您订购的货物将于" + two_days_after +
                        "到达" + shipping.getPortOfDischarge() + "港" +
                        "请凭提货单号" + shipping.getLcNo() + "取货";
        MailUtil.sendMsg(to,subject,content);
        return "redirect:/cargo/shipping/list.do";
    }


    //取消，将状态由已委托变为草稿，2==>1，将委托表中的数据删除
    @RequestMapping(value = "cancel")
    public String cancel(String id){
        //1.根据id查询装箱表
        Packing packing = packingService.findById(id);
        //2.设置装箱单的状态为1
        packing.setState(1);
        //3.将装箱单存回数据库
        packingService.update(packing);
        //4.根据id删除委托表
        shippingService.delete(id);
        return "redirect:/cargo/shipping/list.do";
    }



//    @RequestMapping(value = "/submit")
//    public String submit(String id) throws Exception {
//        //1.根据id查询委托单实体类
//        Shipping shipping = shippingService.findById(id);
//        //2.修改状态
//        shipping.setState(2);
//        shippingService.update(shipping);
//        //3.发送邮件
//        String to = shipping.getNotifyParty();
//        System.out.println("========================================================");
//        System.out.println(to);
//        System.out.println("========================================================");
//        String subject = "委托单";
//        String content = "凭此委托单取货";
//        MailUtil.sendMsg(to,subject,content);
//        return "redirect:/cargo/shipping/list.do";
//    }

    @RequestMapping(value = "/map")
    public String map(String id){
        System.out.println("========================================================");
        System.out.println(id);
        Shipping shipping = shippingService.findById(id);
        System.out.println(shipping);
        System.out.println(shipping.getPortOfLoading());
        System.out.println(shipping.getPortOfDischarge());
        System.out.println("========================================================");
        String portOfLoading = shipping.getPortOfLoading();
        String portOfDischarge = shipping.getPortOfDischarge();
        request.setAttribute("start",portOfLoading);
        request.setAttribute("end",portOfDischarge);
        return "cargo/shipping/map";
    }

}
