package vn.kms.launch.cleancode.Config.Annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Inherited
public @interface NotEmpty {
   boolean value() default true;
   String message() default "Do not leave field empty.";
}
