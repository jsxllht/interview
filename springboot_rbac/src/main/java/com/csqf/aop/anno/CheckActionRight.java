package com.csqf.aop.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//import static java.lang.annotation.ElementType.METHOD;
//import static java.lang.annotation.RetentionPolicy.RUNTIME;
//@Retention(RUNTIME)
//@Target({METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface CheckActionRight {
    //要执行这个方法必须要有的权限
    String value();
}
