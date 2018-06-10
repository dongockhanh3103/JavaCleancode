package vn.kms.launch.cleancode.Config.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)

public @interface FieldLength {
    int maxLength() default -1;
    int minLength() default -1;
    String message() default "Field length is not over expected length.";
}

