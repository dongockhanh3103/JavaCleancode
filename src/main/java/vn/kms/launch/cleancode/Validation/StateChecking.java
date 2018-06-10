package vn.kms.launch.cleancode.Validation;
import vn.kms.launch.cleancode.Config.Annotation.NotEmpty;
import vn.kms.launch.cleancode.Config.Annotation.StateValid;
import vn.kms.launch.cleancode.Constants.Constants;
import vn.kms.launch.cleancode.common.ErrorInfo;

import java.lang.reflect.Field;
import java.util.*;
import java.util.logging.Logger;

public class StateChecking extends BaseValidation<StateValid> {
    private static Logger LOGGER = Logger.getLogger("InfoLogging");

    @Override
    public boolean isValid(String fieldValue, StateValid annotation) {
        return Constants.VALID_STATE_CODE_ITEMS.contains(fieldValue);
    }

    @Override
    public List<ErrorInfo<String, String>> validateChecking(Object object) {
        List<ErrorInfo<String, String>> errorInfoList = new ArrayList<>();
        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(StateValid.class)) {
                StateValid annotation = field.getAnnotation(StateValid.class);
                field.setAccessible(true);
                try {
                    if (!isValid(field.get(object).toString(), annotation)) {
                        String message = "'"+field.get(object).toString()+"'"+"  "+annotation.message();
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
