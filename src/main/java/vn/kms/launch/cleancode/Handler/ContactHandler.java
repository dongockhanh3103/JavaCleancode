package vn.kms.launch.cleancode.Handler;

import vn.kms.launch.cleancode.Config.Annotation.ColunmField;
import vn.kms.launch.cleancode.Constants.Constants;
import vn.kms.launch.cleancode.Contact;
import vn.kms.launch.cleancode.Utils.DatetimeUtil;

import java.lang.reflect.Field;
import java.util.*;

public class ContactHandler {
    public static List<Contact> createContactItems(String[] allLinesContact) {
        List<Contact> allContacts = new ArrayList<>();
        HashMap<String, Integer> headerMapping = mapToColumnDatabase(getDataOfALine(allLinesContact[0]));
        for (int i = 1; i < allLinesContact.length; i++) {
            String[] dataALine = getDataOfALine(allLinesContact[i]);
            if (!isInvalidLine(allLinesContact[i]))
                continue;
            allContacts.add(fillInfoToContact(headerMapping, dataALine));
        }
        return allContacts;
    }

    private static Contact fillInfoToContact(HashMap<String, Integer> headerMapping, String[] dataALine) {
        Contact contact = new Contact();
        for (Field field : Contact.class.getDeclaredFields()) {
            ColunmField annotation = field.getAnnotation(ColunmField.class);
            if (annotation != null) {
                if (annotation.dataType().equals("Integer")) {
                    contact.set(field.getName(), Integer.parseInt(dataALine[getPosColumnData(headerMapping, annotation.value())]));
                    continue;
                }
                contact.set(field.getName(), dataALine[getPosColumnData(headerMapping, annotation.value())]);
            }
        }
        return contact;
    }

    private static HashMap<String, Integer> mapToColumnDatabase(String[] header) {
        HashMap<String, Integer> headerMapping = new HashMap<>();
        for (int i = 0; i < header.length; i++) {
            headerMapping.put(header[i], i);
        }
        return headerMapping;
    }

    private static String[] getDataOfALine(String dataAllLines) {
        return dataAllLines.split("\t");
    }

    private static boolean isInvalidLine(String lineCheck) {
        return !(isBlankLine(lineCheck) || !isLineFormat(getDataOfALine(lineCheck)));
    }



    private static boolean isBlankLine(String lineCheck) {
        return (lineCheck.trim().length() == 0);

    }

    private static int getPosColumnData(Map<String, Integer> headerColumnMapping, String headerName) {
        return headerColumnMapping.get(headerName);

    }

    private static boolean isLineFormat(String data[]) {
        return (data.length == Constants.COLUMN_LINE_SIZE);
    }

}
