package vn.kms.launch.cleancode.Validation;
import vn.kms.launch.cleancode.common.ErrorInfo;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.logging.Logger;

public abstract class BaseValidation<T extends Annotation> {
    private static Logger LOGGER = Logger.getLogger("InfoLogging");
    public  abstract boolean isValid(String fieldValue,T annotation);
    public abstract  List<ErrorInfo<String,String>> validateChecking(Object object);
    static ErrorInfo<String,String> newErrorInfo(String fieldName,String errorLog){
        return  new ErrorInfo<>(fieldName,errorLog);
    }






}
