package com.neuedu.annotation;


import java.lang.annotation.*;

@Target({ElementType.FIELD})         //作用于类
@Retention(RetentionPolicy.RUNTIME)    //运行时可通过反射读取
@Documented     //为注解生成文档
@Inherited      //注解可继承
public @interface Column {

    String value() default "";

    


}
