package org.example.felixlyd.idgenerator.aop;

import org.example.felixlyd.idgenerator.config.CustomOnPropertyCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/10/19
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Inherited
@Conditional(CustomOnPropertyCondition.class)
public @interface CustomConditionalOnProperty {

    String name() default "";

    String[ ] value() default {};
}
