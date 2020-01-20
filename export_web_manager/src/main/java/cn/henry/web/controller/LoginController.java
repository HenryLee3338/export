package cn.henry.web.controller;


import cn.henry.common.utils.HttpClientUtil;
import cn.henry.domain.system.Module;
import cn.henry.domain.system.User;
import cn.henry.service.system.ModuleService;
import cn.henry.service.system.UserService;
import cn.henry.web.shiro.EasyTypeToken;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class LoginController extends BaseController{

    @Autowired
    private UserService userService;

    @Autowired
    private ModuleService moduleService;

	@RequestMapping("/login")
	public String login(String email,String password) {
	    //1.判断邮箱和密码是否为空
        if (StringUtil.isEmpty(email) || StringUtil.isEmpty(password)){
            request.setAttribute("error","用户名或密码不能为空！");
            return "forward:login.jsp";
        }
        try {
        //2.获取shrio的Subject主体
        Subject subject = SecurityUtils.getSubject();
        //3.创建一个UPToken的对象用于存储email和password、
            EasyTypeToken uPToken = new EasyTypeToken(email,password);
        //4.通过subject的login方法进行登录,最终调用AuthRealm中的doGetAuthenticationInfo方法
        subject.login(uPToken);
        //5.通过subject取出关键数据Principal
        User user = (User) subject.getPrincipal();
        //6.将user存入到session当中
        session.setAttribute("loginUser",user);
        //7.通过user找到对应的模块信息
        List<Module> modules = moduleService.findByUser(user);
        //8.将modules存入到session当中
        session.setAttribute("modules",modules);
        //9.转发到主页
        return "/home/main";
        }catch (Exception e){//如果登录失败
            //转发到登录页面
            request.setAttribute("error","用户名或密码错误");
            return "forward:login.jsp";
        }
    }

    //退出
    @RequestMapping(value = "/logout",name="用户登出")
    public String logout(){
        SecurityUtils.getSubject().logout();   //登出
        return "forward:login.jsp";
    }

    @RequestMapping("/home")
    public String home(){
	    return "home/home";
    }



//====微信登录=================================================================================================================================
    //扫码后跳到这个方法当中，并且判断数据库里面有没有这个微信账号的openid
    @RequestMapping("/callback")
    public String callback(String code){
        //这里可以判断state是否改变判断此次请求是否安全
        //得到code换取token的地址
        String getTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid=wx3bdb1192c22883f3&secret=db9d6b88821df403e5ff11742e799105&code=" + code + "&grant_type=authorization_code";
        //得到地址后通过工具类得到token
        String tokenResult = HttpClientUtil.doGet(getTokenUrl);
        //得到的token是json格式，将他转成map，得到换取用户资源的openid和access_token
        Map<String, String> tokenResultMap = JSON.parseObject(tokenResult, Map.class);
        String tokenString = tokenResultMap.get("access_token");
        //根据转换成的map集合得到openid
        String openId = tokenResultMap.get("openid");
        //通过openid去数据库查询是否有该用户
        User user = userService.findByOpenId(openId);
        System.out.println(user);

        //如果有该用户，user不为空 ，代码里面直接调用shiro的登录 ----> 直接跳转主页
        if (user != null) {
            String email = user.getEmail();
            //这里用的就是我覆写的，并且通过自定义的拦截器，跳过密码的验证
            EasyTypeToken token = new EasyTypeToken(email);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);//调用此方法最终会调用AuthRealm中的doGetAuthenticationInfo身份验证(登录)方法
                                //身份验证成功以后即可登录并且获取相应的权限
            session.setAttribute("user",user);//在session中存入user
            List<Module> modules = moduleService.findByUser(user);//去数据库查找该用户有权限的模块
            session.setAttribute("modules",modules);//将模块传回前端页面，前端就会显示相应的模块
            return "home/main";//返回主页

        } else {
            //如果没有该用户，user为空，说明这个openid没有绑定过，那么久要跳转到绑定页面
            request.setAttribute("openid",openId);//将微信账号生成的openId放入request传回前端
            return "forward:wLogin.jsp";//返回到wLogin，账户绑定微信的页面，填入邮箱和密码之后会跳跳入到下面的wLogin方法中
        }

    }

    //在微信与账户绑定页面提交表单会跳到此方法当中
    @RequestMapping("/wLogin")
    public String  wLogin(String email,String password,String openid,String headimgurl,String nickname) {
        //两个微信可能会同时绑定一个用户，所以需要判断一下用户的openid是不是为零
        User user = null;
        Subject subject = SecurityUtils.getSubject();
        try {
            user = userService.findByEmail(email);
            //判断该用户是不是绑定了openid
            if (user.getOpenId() != null) {//得到的openid不为空，说名该账户已经绑定了微信，不能再次绑定
                request.setAttribute("error", "该用户已经绑定其他微信");
                return "forward:wLogin.jsp";//带着错误信息返回微信绑定页面
            }

            //如果该用户没有openid的话，进行下面的方法
            //用subject的login方法进行登录判断，输入你的用户名和密码
            EasyTypeToken upToken = new EasyTypeToken(email, password);
            subject.login(upToken);

            user.setOpenId(openid);
            userService.updatewx(user);//用update方法的话会加密两次，所以重写了一个updatewx方法，用update方法总是把密码改了
            session.setAttribute("user", user);
            List<Module> modules = moduleService.findByUser(user);
            session.setAttribute("modules", modules);
            return "home/main";
        } catch (AuthenticationException e) {
            request.setAttribute("error", "用户名或密码错误");
            return "forward:wLogin.jsp";
        }
    }
}
