package vn.kms.launch.cleancode.Handler;

import vn.kms.launch.cleancode.Config.Annotation.*;
import vn.kms.launch.cleancode.Contact;
import vn.kms.launch.cleancode.Validation.*;
import vn.kms.launch.cleancode.common.ErrorInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ContactValidator {
    public static Map<Integer, List<List<ErrorInfo<String, String>>>> contactsInvalid(List<Contact> contactItems) {
        Map<Integer, List<List<ErrorInfo<String, String>>>> invalidContacts = new TreeMap<>();
        for (Contact contact : contactItems) {
            if (!getErrorLog(contact).isEmpty()) {
                invalidContacts.put(contact.getId(), getErrorLog(contact));
            }
        }
        return invalidContacts;
    }

    public static List<Contact> contactsValid(List<Contact> allContacts) {
        List<Contact> contactValid = new ArrayList<>();
        allContacts.forEach(contact -> {
            if (getErrorLog(contact).isEmpty()) {
                contactValid.add(contact);
            }
        });
        return contactValid;
    }

    private static List<List<ErrorInfo<String, String>>> getErrorLog(Contact contact) {
        List<List<ErrorInfo<String, String>>> errorLog = new ArrayList<>();
        BaseValidation<DatetimeFormat> datetimeFormatBaseValidation = new DateTimeChecking();
        BaseValidation<ValidLength> lengthBaseValidation = new LengthChecking();
        BaseValidation<Pattern> patternBaseValidation = new PatternChecking();
        BaseValidation<StateValid> stateValidBaseValidation = new StateChecking();
        BaseValidation<NotEmpty> notEmptyBaseValidation = new NotEmptyChecking();

        List<BaseValidation> baseValidationsList = new ArrayList<>();
        baseValidationsList.add(datetimeFormatBaseValidation);
        baseValidationsList.add(lengthBaseValidation);
        baseValidationsList.add(patternBaseValidation);
        baseValidationsList.add(stateValidBaseValidation);
        baseValidationsList.add(notEmptyBaseValidation);
        baseValidationsList.forEach(baseValidation -> {
            if(!baseValidation.validateChecking(contact).isEmpty()){
                errorLog.add(baseValidation.validateChecking(contact));
            }
        });
        return errorLog;
    }


}
