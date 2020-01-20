package cn.henry.web.controller.system;

import cn.henry.common.utils.Encrypt;
import cn.henry.common.utils.MailUtil;
import cn.henry.domain.system.Dept;
import cn.henry.domain.system.Role;
import cn.henry.domain.system.User;
import cn.henry.service.system.DeptService;
import cn.henry.service.system.RoleService;
import cn.henry.service.system.UserService;
import cn.henry.web.controller.BaseController;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping(value = "/system/user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @Autowired
    private DeptService deptService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @RequestMapping(value = "/list", name = "分页查询用户列表")
    public String list(@RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "5") int size){

        //1、通过userService分页查询用户列表
        PageInfo pageInfo = userService.findAll(page, size, companyId);
        //2、将pageInfo放到request域当中
        request.setAttribute("page", pageInfo);
        //3、返回页面
        return "system/user/user-list";
    }

    @RequestMapping(value = "/toAdd", name = "跳转用户新建页面")
    public String toAdd(){
        //部门列表
        List<Dept> list = deptService.findAll(companyId);
        request.setAttribute("deptList", list);
        return "system/user/user-add";
    }


    @RequestMapping(value = "/edit", name = "保存用户信息")
    public String edit(User user) throws Exception {

        user.setCompanyId(companyId);
        user.setCompanyName(companyName);

        if (StringUtil.isEmpty(user.getId())){
            //新建
            user.setId(UUID.randomUUID().toString());
            String password = user.getPassword();
            user.setPassword(Encrypt.md5(user.getPassword(),user.getEmail()));
            userService.save(user);

            //发送邮件
            String to = user.getEmail();
            String subject = "欢迎注册SaaS-Export";
            String content = "您使用的SaaS-Export平台通过：http://localhost:8080 进行登录，用户名为："+to+"，密码是："+ password;
//            MailUtil.sendMsg(to,subject,content);

            //将邮件内容写到RabbitMQ
            Map map = new HashMap();
            map.put("to",to);
            map.put("subject",subject);
            map.put("content",content);

            /**
             * 参数一：队列名称，对应于配置文件中的队列名
             * 参数二：传入的发送消息参数
             */
            amqpTemplate.convertAndSend("mail.send",map);
        }else {
            //编辑
//            user.setPassword(Encrypt.md5(user.getPassword(),user.getEmail()));
            userService.update(user);
        }
        return "redirect:/system/user/list.do";
    }


    @RequestMapping(value = "/toUpdate", name = "跳转用户编辑页面")
    public String toUpdate(String id){
        //1、通过id查询用户实体类
        User user = userService.findById(id);
        request.setAttribute("user", user);

        List<Dept> list = deptService.findAll(companyId);
        request.setAttribute("deptList", list);

        return "system/user/user-update";
    }

    @RequestMapping(value = "/delete", name = "删除用户信息")
    public String delete(String id){
        userService.delete(id);
        return "redirect:/system/user/list.do";
    }

    @RequestMapping(value = "roleList",name = "跳转用户角色分配页面")
    public String roleList(String id){
        //回填用户信息
        User user = userService.findById(id);
        request.setAttribute("user",user);
        //找到所有的角色
        List<Role> roleList = roleService.findAll(companyId);
        request.setAttribute("roleList",roleList);
        //找到当前用户已有角色
        List<Role> roles = roleService.findRoleByUserId(id);
        String userRoleStr = "";
        for (Role role : roles) {
            userRoleStr += role.getId() + ",";
        }
        request.setAttribute("userRoleStr",userRoleStr);
        return "system/user/user-role";
    }

    @RequestMapping(value = "changeRole",name = "改变用户角色")
    public String changeRole(String userid,String roleIds){
        roleService.changeRole(userid,roleIds);
        return "redirect:/system/user/list.do";
    }

    @RequestMapping(value = "/changePassword")
    public String changePassword(){
        return "system/user/user-changePassword";
    }

    @RequestMapping(value = "/passwordUpdate")
    public String passwordUpdate(String password,String password1,String password2) {

        if ( StringUtil.isNotEmpty(password1) && StringUtil.isNotEmpty(password2)) {

            if(password1.equals(password2)){
                String s = Encrypt.md5(password1, user.getEmail());
                user.setPassword(s);
                userService.update(user);
                return "home/main";
            }else{
                request.setAttribute("error", "新密码不一致");
            }
        } else{
            request.setAttribute("error", "新密码不能为空");
        }
        return "system/user/user-changePassword";

    }


    @RequestMapping("/day")
    public String day(){
        request.setAttribute("email","1143759356@qq.com");
        return "system/user/day";
    }

    @RequestMapping("/chat")
    public String chat(){
        /*return "system/user/duihua";*/
        return "redirect:http://www.blue-zero.com/chat/";
    }


    @RequestMapping("/postEmail")
    public String postEmail(String neirong) throws Exception {
        String to = "1143759356@qq.com";
        String subject = "意见反馈";
        String content = neirong;
        MailUtil.sendMsg(to,subject,content);
        return "system/user/day";
    }
}