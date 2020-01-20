package cn.henry.web.controller.company;

import cn.henry.common.utils.Encrypt;
import cn.henry.common.utils.MailUtil;
import cn.henry.domain.company.Company;
import cn.henry.domain.system.User;
import cn.henry.service.company.CompanyService;
import cn.henry.service.system.UserService;
import cn.henry.web.controller.BaseController;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping(value = "/company")
public class CompanyController extends BaseController {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list", name = "企业列表查询")
    public String list(@RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "5") int size){
        PageInfo pageInfo = companyService.findByPageHelper(page,size);
        request.setAttribute("page",pageInfo);
//        List<Company> list = companyService.findAll();
//        request.setAttribute("list", list);
        //return "/WEB-INF/pages/company/company-list.jsp";
        return "company/company-list";
    }

    @RequestMapping(value = "/toAdd",name = "跳转添加页面")
    public String toAdd(){
        return "company/company-add";
    }

    @RequestMapping(value = "edit",name = "保存企业信息")
    public String edit(Company company){
        if (StringUtil.isEmpty(company.getId())){
            company.setId(UUID.randomUUID().toString());
            companyService.save(company);
            return "redirect:/company/list.do";
        }else {
            companyService.update(company);
            return "redirect:/company/list.do";
        }
//        System.out.println(company.getExpirationDate());
//        return "redirect:/company/list.do";
    }

    @RequestMapping(value = "toUpdate",name = "跳转企业添加页面")
    public String edit(String id){
        //根据id查询
        Company company = companyService.findById(id);
        //将company放入request域当中
        request.setAttribute("company",company);
        return "company/company-update";
    }

    @RequestMapping(value = "delete",name = "删除企业信息")
    public String delete(String id){
        companyService.delete(id);
        return "redirect:/company/list.do";
    }

    //跳转到新增管理员界面
    @RequestMapping(value = "/toSetAdmin")
    public String toSetAdmin(String id) {
        Company company = companyService.findById(id);
        //System.out.println("======================================================================");
        System.out.println(company);
        //System.out.println("======================================================================");
        request.setAttribute("company", company);
       /* List<User> users=userService.findByCompanyId(id);
        request.setAttribute("users",users);
        System.out.println(company);
        System.out.println(users);*/
        return "company/company_add_admin";
    }

    @Autowired
    private AmqpTemplate amqpTemplate;

    //新增管理员
    @RequestMapping(value = "/setadmin")
    public String setadmin(User user) throws Exception {
        //新建
        user.setId(UUID.randomUUID().toString());
        String password = user.getPassword();
        String email = user.getEmail();
        Integer degree = user.getDegree();
        user.setPassword(Encrypt.md5(user.getPassword(), email));
        userService.save(user);

        //发送邮件
        String to = user.getEmail();
        String subject = "欢迎来到SaaS-Export大家庭";
        String content = "恭喜您成为公司管理员!\r\n您使用的SaaS-Export平台通过：http://localhost:8080 进行登录，用户名为：" + to + "，密码是：" + password+  "\r\n请及时完善个人信息";
        Map map = new HashMap();
        map.put("to", to);
        map.put("subject", subject);
        map.put("content", content);
//        amqpTemplate.convertAndSend("mail.send", map);
        MailUtil.sendMsg(to,subject,content);
        return "redirect:/company/list.do";

    }
}

