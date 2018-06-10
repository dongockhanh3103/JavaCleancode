package vn.kms.launch.cleancode.Config.Annotation;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
@Inherited
public @interface ValidLength {
    int maxLength();
    String message() default " length is not over expected length ";
}