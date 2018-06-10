package vn.kms.launch.cleancode.Validation;

import vn.kms.launch.cleancode.Config.Annotation.DatetimeFormat;
import vn.kms.launch.cleancode.Config.Annotation.NotEmpty;
import vn.kms.launch.cleancode.common.ErrorInfo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class NotEmptyChecking extends BaseValidation<NotEmpty> {
    private static Logger LOGGER = Logger.getLogger("InfoLogging");

    @Override
    public boolean isValid(String fieldValue, NotEmpty annotation) {
        return fieldValue.trim().length() != 0;
    }

    @Override
    public List<ErrorInfo<String, String>> validateChecking(Object object) {
        List<ErrorInfo<String, String>> errorInfoList = new ArrayList<>();
        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(NotEmpty.class)) {
                NotEmpty annotation = field.getAnnotation(NotEmpty.class);
                field.setAccessible(true);
                try {
                    if (!isValid(field.get(object).toString(), annotation)) {
                        String message = "'"+field.get(object).toString()+"'" + "  " + annotation.message();
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
