package vn.kms.launch.cleancode.Config.Annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
@Inherited
public @interface Pattern {
    String value() default "";
    String message() default "invalid format ";
    String phoneFormat() default "";
}