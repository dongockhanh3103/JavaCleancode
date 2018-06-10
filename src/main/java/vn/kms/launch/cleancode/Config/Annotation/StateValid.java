package vn.kms.launch.cleancode.Config.Annotation;

import java.lang.annotation.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
@Inherited
public @interface StateValid {

    String message() default "incorrect state code";
}