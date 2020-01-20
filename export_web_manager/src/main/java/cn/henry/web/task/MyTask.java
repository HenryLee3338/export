package cn.henry.web.task;

import cn.henry.domain.system.Dept;
import cn.henry.domain.vo.FactoryAmountVo;
import cn.henry.service.system.DeptService;
import cn.henry.service.system.FactoryAmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class MyTask {

//    @Autowired
//    private DeptService deptService;
    @Autowired
    private FactoryAmountService factoryAmountService;

    public void execute(){

//        List<Dept> all = deptService.findAll("1");
//        System.out.println(all);


//        System.out.println("====================================================================================");
        System.out.println("当前时间: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//        List<FactoryAmountVo> list = factoryAmountService.groupByFactoryId();
//
//        System.out.println(list);
//        System.out.println("====================================================================================");

    }
}
