package vn.kms.launch.cleancode.Validation;
import vn.kms.launch.cleancode.Config.Annotation.DatetimeFormat;
import vn.kms.launch.cleancode.common.ErrorInfo;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

public class DateTimeChecking extends BaseValidation<DatetimeFormat> {
    private static Logger LOGGER = Logger.getLogger("InfoLogging");

    @Override
    public boolean isValid(String fieldValue, DatetimeFormat annotation) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(annotation.regex());
        simpleDateFormat.setLenient(false);
        if(fieldValue.length()!=annotation.regex().length()){
            return false;
        }
        try {
            Date javaDate = simpleDateFormat.parse(fieldValue);
        }catch (ParseException e){
            return false;
        }
        return true;
    }

    @Override
    public List<ErrorInfo<String, String>> validateChecking(Object object) {
        List<ErrorInfo<String,String>> errorInfoList=new ArrayList<>();
        for(Field field: object.getClass().getDeclaredFields()){
            if(field.isAnnotationPresent(DatetimeFormat.class)){
                DatetimeFormat annotation = field.getAnnotation(DatetimeFormat.class);
                field.setAccessible(true);
                try {
                    if(!isValid(field.get(object).toString(),annotation)){
                        String message ="'"+field.get(object).toString()+"'"+"  "+annotation.message()+"   "+annotation.regex();
                        errorInfoList.add(newErrorInfo(field.getName(),message));
                    }
                } catch (IllegalAccessException e) {
                    LOGGER.info(e.getMessage());
                }
            }
        }
        return errorInfoList;
    }

}
