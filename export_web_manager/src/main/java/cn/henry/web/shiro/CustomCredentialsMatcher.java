package cn.henry.web.shiro;

import cn.henry.common.utils.Encrypt;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.subject.PrincipalCollection;

//自定义的密码比较，继承自shiro的密码比较器
public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {

    /**
     * 密码比较
     *
     * @param token: 存储有前端传来的email和password
     * @param info:  存储有通过email查询到的user和password
     * @return
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
//        //1.将token转换为UsernamePasswordToken类型
//        UsernamePasswordToken uPToken = (UsernamePasswordToken) token;
//        //2.从token中取出password并且转换为String类型
//        String password = String.valueOf(uPToken.getPassword());
//        String email = uPToken.getUsername();
//        //3.将password进行加密: 固定值+盐(password+email)
//        String md5Password = Encrypt.md5(password, email);
//
//        //4.从info中获取数据库中的密码
//        String dbPassword = String.valueOf(info.getCredentials());
//
//        //5.比较两个密码返回布尔值
//        return md5Password.equals(dbPassword);





        EasyTypeToken upToken = (EasyTypeToken) token;

        if (upToken.getType()==LoginType.NOPASSWD){
            return true;
        }

        //用String.valueOf进行转化
        String userPassword = String.valueOf(upToken.getPassword());
        String email = upToken.getUsername();

        //userPassword进行加密处理，md5进行加密
        //md5不可逆的加密方式：
        //123  -->  xxxxxx    1-->xx 2-->xx 3-->xx
        //输入的密码+一些固定值 --> md5   通过网站解密得到输入的密码+固定值
        //管固定值叫做加盐   UUID  123456(9999)   123456(8888)
        //通过密码+邮箱进行加密处理
        String md5Password = Encrypt.md5(userPassword, email);

        //2. 获取数据库密码
        //用String.valueOf进行转化
        String dbPassword = String.valueOf(info.getCredentials());

        //比较 数据库密码 和 用户输入的密码，一致，密码成功，不一致，密码不对
        return md5Password.equals(dbPassword);

    }
}