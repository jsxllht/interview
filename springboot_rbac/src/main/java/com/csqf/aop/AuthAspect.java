package com.csqf.aop;

import com.csqf.aop.anno.CheckActionRight;
import com.csqf.common.exception.AppException;
import com.csqf.common.result.ResponseEnum;
import com.csqf.config.JWTConfig;
import com.csqf.pojo.dto.LoginDTO;
import com.csqf.service.RoleService;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;


//@Aspect:作用是把当前类标识为一个切面供容器读取
//
//@Pointcut：Pointcut是植入Advice的触发条件。每个Pointcut的定义包括2部分，一是表达式，二是方法签名。方法签名必须是 public及void型。可以将Pointcut中的方法看作是一个被Advice引用的助记符，因为表达式不直观，因此我们可以通过方法签名的方式为 此表达式命名。因此Pointcut中的方法只需要方法签名，而不需要在方法体内编写实际代码。
//@Around：环绕增强，相当于MethodInterceptor
//@AfterReturning：后置增强，相当于AfterReturningAdvice，方法正常退出时执行
//@Before：标识一个前置增强方法，相当于BeforeAdvice的功能，相似功能的还有
//@AfterThrowing：异常抛出增强，相当于ThrowsAdvice
//@After: final增强，不管是抛出异常或者正常退出都会执行

@Component
@Aspect
public class AuthAspect {
    @Autowired
    private JWTConfig jwtConfig;
    @Autowired
    private RoleService roleService;


    @Around("@annotation(com.csqf.aop.anno.CheckLogin)")
    public Object beforeCheckLogin(ProceedingJoinPoint joinPoint) throws Throwable {
        // 1 从请求头中获得令牌  request
        //①在方法中获取请求上下文
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //② 获取请求request
        HttpServletRequest request = requestAttributes.getRequest();
        //③获取请求头参数
        String token = request.getHeader("X-Token");

        // 2 如果令牌为空 null  ""  true
        if(StringUtils.isEmpty(token)==true){
            throw new AppException(ResponseEnum.HAS_NO_TOKEN);
        }

        // 3 解析临牌
        LoginDTO loginDTO = jwtConfig.checkJwt(token);
        //有异常就统一处理
        Integer roleid = loginDTO.getRoleid();
        Long id = loginDTO.getId();
        request.setAttribute("roleid",roleid);
        request.setAttribute("id",id);
        // 4 接着往下执行
        return joinPoint.proceed();
    }
    @Around("@annotation(com.csqf.aop.anno.CheckActionRight)")
    @ApiOperation("获取权限")
    public Object beforeCheckRight(ProceedingJoinPoint joinPoint) throws Throwable {
        // 1 执行令牌 获得角色id
        //①在方法中获取请求上下文
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //② 获取请求request
        HttpServletRequest request = requestAttributes.getRequest();
        //③获取请求头参数
        String token = request.getHeader("X-Token");


        if(StringUtils.isEmpty(token)==true){
            throw new AppException(ResponseEnum.HAS_NO_TOKEN);
        }
        LoginDTO loginDTO = jwtConfig.checkJwt(token);
        Integer roleid = loginDTO.getRoleid();


        // 2从角色id 获得这个角色拥有的所有行为权限码的集合
        List<String> codes = roleService.findRigthCodeByRoleid(roleid);

        //3获得切入点方法需要的权限码 记录再注解里面
        //有个JoinPoint类，joinPoint.getSignature()用来获取代理类和被代理类的信息。
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        //  System.out.println(signature);
        //R com.csqf.controller.admin.AdminRightController.CheckSearch()
        //返回值类型 及方法的包名加方法名
        //R com.csqf.controller.admin.AdminRightController.CheckAaudit()
        Method method = signature.getMethod();
        //        System.out.println(method);
        //public com.csqf.common.result.R com.csqf.controller.admin.AdminRightController.CheckSearch()
        //返回值包名加方法名 及方法的包名加方法名
        //public com.csqf.common.result.R com.csqf.controller.admin.AdminRightController.CheckAaudit()
        CheckActionRight annotation = method.getDeclaredAnnotation(CheckActionRight.class);
//        System.out.println(annotation);
        //@com.csqf.aop.anno.CheckActionRight(value=TEST_AUDIT)
        //通过反射得到CheckActionRight注解的公有方法
        //@com.csqf.aop.anno.CheckActionRight(value=TEST_AUDIT)
        String code = annotation.value();
//        System.out.println(code);
        //TEST_SEARCH
        //TEST_AUDIT
        //4 判断集合中是否包含权限码
        if (codes.contains(code)==false){
            throw new AppException(ResponseEnum.HAS_NO_RIGHT);
        }
        //放行
        return joinPoint.proceed();
    }
}
