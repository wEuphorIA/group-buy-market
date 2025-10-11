package cn.bugstack.types.annoations;


import java.lang.annotation.*;

/**
 @author Euphoria
 @version 1.0
 @description: 注解，动态配置中心标记
 @date 2025/10/11 下午11:12 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface DCCValue {
    String value() default "";
}
