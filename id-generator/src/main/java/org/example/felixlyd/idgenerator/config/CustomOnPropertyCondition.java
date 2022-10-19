package org.example.felixlyd.idgenerator.config;

import org.example.felixlyd.idgenerator.aop.CustomConditionalOnProperty;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/10/19
 */
public class CustomOnPropertyCondition implements Condition {

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        Map<String, Object> annotationAttributes = annotatedTypeMetadata.getAnnotationAttributes(CustomConditionalOnProperty.class.getName());
        if (annotationAttributes == null) {
            return false;
        }

        String propertyName = (String) annotationAttributes.get("name");
        if (propertyName == null || propertyName.length() == 0) {
            return false;
        }

        String[] values = (String[]) annotationAttributes.get("value");
        if (values == null || values.length == 0) {
            return false;
        }

        String propertyValue = conditionContext.getEnvironment().getProperty(propertyName);
        if (propertyValue == null || propertyValue.length() == 0) {
            return false;
        }

        // 有一个匹配上就ok
        for (String havingValue : values) {
            if (propertyValue.equalsIgnoreCase(havingValue)) {
                return true;
            }
        }
        return false;
    }
}