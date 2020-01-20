package cn.henry.web.shiro;

import cn.henry.domain.system.Module;
import cn.henry.domain.system.User;
import cn.henry.service.system.ModuleService;
import cn.henry.service.system.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AuthRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Autowired
    private ModuleService moduleService;

//    /**
//     * 身份认证(登录)
//     * LoginController中的login中的subject.login(UPToken)方法最终会调用此方法
//     * @param authenticationToken
//     * @return AuthenticationInfo
//     * @throws AuthenticationException
//     */
//   protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        //1.将传入的token参数转化为UsernamePasswordToken
//        UsernamePasswordToken uPToken = (UsernamePasswordToken)authenticationToken;
//        //2.通过token获取email
//        String email = uPToken.getUsername();
//        //3.通过email去数据库查询user对象
//        User user = userService.findByEmail(email);
//        //4.创建返回对象
//            //Object principal：直译：关键数据，对于shiro来讲名称叫安全数据，一般我们将User对象存入安全数据
//            //Object credentials：直译：证书，对于shiro来讲也叫证书，一般我们将数据库密码存入证书
//            //String realmName：随便起名，一般将方法返回值写入
//        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(),"AuthenticationInfo");
//        return info;
//    }
//
//
//
//
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        //1.通过principals取出user对象
//        User user = (User) principals.getPrimaryPrincipal();
//        //2.查询user对象的所有的模块信息
//        List<Module> modules = moduleService.findByUser(user);
//        //3.将用户所拥有的模块权限的名字(module.getName())遍历传入到perm里
//        Set<String> perm = new HashSet<String>();
//        for (Module module : modules) {
//            perm.add(module.getName());
//        }
//        //4.返回
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.setStringPermissions(perm);
//        return info;
//    }


    /**
     * 身份认证(登录)
     * LoginController中的login中的subject.login(UPToken)方法最终会调用此方法
     * @param token
     * @return AuthenticationInfo
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //通过LoginController，login.do方法，subject.login访问到该方法
        //1、将传入的token参数转化为UsernamePasswordToken
        EasyTypeToken upToken = (EasyTypeToken) token;

        //2、通过token获取email
        String email = upToken.getUsername();

        //3、通过email直接获取user对象
        User user = userService.findByEmail(email);

        //Object principal：直译：关键数据，对于shiro来讲名称叫安全数据，一般我们将User对象存入安全数据
        //Object credentials：直译：证书，对于shiro来讲也叫证书，一般我们将数据库密码存入证书
        //String realmName：随便起名，一般将方法返回值写入
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), "AuthenticationInfo");
        return info;
    }



    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //1. 通过principals查找到User对象
        User user = (User) principals.getPrimaryPrincipal();

        //2. 通过User对象查询所有的模块信息
        List<Module> modules = moduleService.findByUser(user);

        //3. 将模块信息传入到perm里
        Set<String> perm = new HashSet<String>();
        for (Module module : modules) {
            perm.add(module.getName());
        }

        //返回的授权信息中包含了所有的模块名称
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(perm);
        return info;
    }





}
