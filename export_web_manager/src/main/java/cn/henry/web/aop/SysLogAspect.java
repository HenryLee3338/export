package cn.henry.web.aop;

import cn.henry.domain.system.SysLog;
import cn.henry.domain.system.User;
import cn.henry.service.system.SysLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

@Component
@Aspect
public class SysLogAspect {
    @Autowired
    private SysLogService sysLogService;
    @Autowired
    private HttpSession session;
    @Autowired
    private HttpServletRequest request;

    //代理方法
    @Around(value = "execution( * cn.henry.web.controller.*.*.*(..))")
    public Object saveLog(ProceedingJoinPoint pjp) throws Throwable {
        //创建日志
        SysLog sysLog = new SysLog();
        //设置日志id
        sysLog.setId(UUID.randomUUID().toString());
        //取出session中的用户
        User user = (User) session.getAttribute("loginUser");
        //通过被代理对象获取签名
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        //通过签名获取方法
        Method method = signature.getMethod();
        //通过方法获取方法上的requestMapping注解
        RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);

        if (user!=null&&requestMapping!=null){
            //为日志对象赋值
            sysLog.setUserName(user.getUserName());
            sysLog.setCompanyId(user.getCompanyId());
            sysLog.setCompanyName(user.getCompanyName());
            sysLog.setTime(new Date());
            sysLog.setIp(request.getLocalAddr());
            sysLog.setMethod(method.getName());
            sysLog.setAction(requestMapping.name());
            //将日志写入数据库
            sysLogService.save(sysLog);
        }
        //执行被代理方法，比如list.do
        return pjp.proceed();
    }
}
