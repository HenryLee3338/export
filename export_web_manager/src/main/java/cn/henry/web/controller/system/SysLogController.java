package cn.henry.web.controller.system;

import cn.henry.service.system.SysLogService;
import cn.henry.web.controller.BaseController;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/system/log")
public class SysLogController extends BaseController {
    @Autowired
    private SysLogService sysLogService;
    @RequestMapping(value = "/list",name = "查看日志列表")
    public String list(@RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "5") int size){
        PageInfo pageInfo = sysLogService.findAll(companyId,page,size);
        request.setAttribute("page",pageInfo);
        return "system/log/log-list";
    }
}
