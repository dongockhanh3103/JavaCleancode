package vn.kms.launch.cleancode.Validation;

import vn.kms.launch.cleancode.Config.Annotation.Pattern;
import vn.kms.launch.cleancode.Config.Annotation.StateValid;
import vn.kms.launch.cleancode.common.ErrorInfo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class PatternChecking extends BaseValidation<Pattern> {
    private static Logger LOGGER = Logger.getLogger("InfoLogging");

    @Override
    public boolean isValid(String fieldValue, Pattern annotation) {
        return fieldValue.matches(annotation.value());
    }

    @Override
    public List<ErrorInfo<String, String>> validateChecking(Object object) {
        List<ErrorInfo<String, String>> errorInfoList = new ArrayList<>();
        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Pattern.class)) {
                Pattern annotation = field.getAnnotation(Pattern.class);
                field.setAccessible(true);
                try {
                    if (!isValid(field.get(object).toString(), annotation)) {
                        String message = "'"+field.get(object).toString()+"'"+ "  " + annotation.message() + "   " + annotation.phoneFormat();
                        errorInfoList.add(newErrorInfo(field.getName(), message));
                    }
                } catch (IllegalAccessException e) {
                    LOGGER.info(e.getMessage());
                }
            }
        }
        return errorInfoList;
    }
}
