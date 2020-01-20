package cn.henry.web.controller;

import cn.henry.domain.system.User;
import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BaseController {
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;
    protected User user;

//    以下两项以后通过session获取
    protected String companyId;
    protected String companyName;
    @ModelAttribute
    public void init(HttpServletRequest request,HttpServletResponse response,HttpSession session){
        this.request = request;
        this.response = response;
        this.session = session;

        User user = (User) session.getAttribute("loginUser");

        if (user != null){
            this.user = user;
            this.companyId = user.getCompanyId();
            this.companyName = user.getCompanyName();
        }
//        this.companyId = "1";
//        this.companyName = "传智播客教育股份有限公司";
    }
}
