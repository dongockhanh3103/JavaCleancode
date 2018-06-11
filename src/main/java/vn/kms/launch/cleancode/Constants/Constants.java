package vn.kms.launch.cleancode.Constants;

import java.util.*;

public class Constants {
    public  static  final Set<String> VALID_STATE_CODE_ITEMS = new HashSet<>(Arrays.asList("AL", "AK", "AS", "AZ", "AR",
            "CA", "CO", "CT", "DE", "DC", "FL", "GA", "GU", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD",
            "MH", "MA", "MI", "FM", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "MP", "OH",
            "OK", "OR", "PW", "PA", "PR", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "VI", "WA", "WV", "WI", "WY"));
    public static  String getReportHeaderItem (String keyReport){
        Map<String,String> REPORT_HEADERS_ITEMS=new HashMap<>();
        REPORT_HEADERS_ITEMS.put("invalid-contact-details", "contact_id\terror_field\terror_message\r\n");
        REPORT_HEADERS_ITEMS.put("invalid-contact-summary", "field_name\tnumber_of_invalid_contact\r\n");
        REPORT_HEADERS_ITEMS.put("contact-per-state", "state_code\tnumber_of_contact\r\n");
        REPORT_HEADERS_ITEMS.put("contact-per-age-group", "group\tnumber_of_contact\tpercentage_of_contact\r\n");
        return REPORT_HEADERS_ITEMS.get(keyReport);
    }
    public static final String CONTACT_VALID_FILE_NAME="valid-contacts";
    public static final String INVALID_CONTACT_DETAILS_FILE_NAME="invalid-contact-details";
    public static final String INVALID_CONTACT_SUMMARY_FILE_NAME="invalid-contact-summary";
    public static final String CONTACT_PER_STATE_FILE_NAME="contact-per-state";
    public static final String CONTACT_PER_AGE_GROUP_FILE_NAME="contact-per-age-group";

    public static final int YEAR_OF_REPORT = 2016;
    public static final int MAX_CHARACTER_FILE_SIZE = 100000;
    public static final int COLUMN_LINE_SIZE = 14;



}
