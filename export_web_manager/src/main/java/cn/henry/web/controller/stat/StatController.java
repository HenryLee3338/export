package cn.henry.web.controller.stat;

import cn.henry.service.stat.StatService;
import cn.henry.web.controller.BaseController;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/stat")
public class StatController extends BaseController {

    @Reference
    private StatService statService;

    @RequestMapping(value = "/demo")
    public String Demo(){
        return "stat/stat-demo";
    }

    @RequestMapping(value = "/toCharts")
    public String toCharts(String chartsType){
        //chartsType = factory
        return "stat/stat-" + chartsType;
    }

    @RequestMapping(value = "/getFactoryData")
    @ResponseBody
    public List<Map> getFactoryData(){
        //获取数据
        List<Map> factoryData = statService.getFactoryData(companyId);
        System.out.println(factoryData);
        return statService.getFactoryData(companyId);
    }

    @RequestMapping(value = "/getOnlineData")
    @ResponseBody
    public List<Map> getOnlineData(){
        List<Map> onlineData = statService.getOnlineData(companyId);
        System.out.println(onlineData);
        return statService.getOnlineData(companyId);
    }

    @RequestMapping(value = "/getSellData")
    @ResponseBody
    public List<Map> getSellData(){
        List<Map> sellData = statService.getSellData(companyId);
        System.out.println(sellData);
        return statService.getSellData(companyId);
    }


    //销售单价排行
    @RequestMapping(value = "/getPriceData")
    @ResponseBody
    public List<Map> getPriceData() {
        List<Map> priceData = statService.getPriceData(companyId);
        return priceData;
    }

    @RequestMapping(value = "/getlojinipData")
    @ResponseBody
    public List<Map> getlojinipData(){
        List<Map> loginData = statService.getlojinipData(companyId);
        return loginData;
    }
}
