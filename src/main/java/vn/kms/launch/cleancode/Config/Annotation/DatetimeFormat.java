package vn.kms.launch.cleancode.Config.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DatetimeFormat {
    String regex() default "MM/dd/yyyy";
    String message() default "Datetime is not format ";
}