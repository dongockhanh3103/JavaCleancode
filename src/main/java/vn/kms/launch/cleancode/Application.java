package vn.kms.launch.cleancode;

import vn.kms.launch.cleancode.Config.Annotation.ColunmField;
import vn.kms.launch.cleancode.Handler.ContactHandler;
import vn.kms.launch.cleancode.Handler.ContactValidator;
import vn.kms.launch.cleancode.Report.ContactInvalidDetailReport;
import vn.kms.launch.cleancode.Report.ContactPerStateReport;
import vn.kms.launch.cleancode.Report.ContactValidReport;
import vn.kms.launch.cleancode.Report.ReportService;
import vn.kms.launch.cleancode.Utils.FileUtil;
import vn.kms.launch.cleancode.Utils.SortUtil;
import vn.kms.launch.cleancode.common.ErrorInfo;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.logging.Logger;


public class Application {

    private static Logger LOGGER = Logger.getLogger("InfoLogging");

    public static void main(String[] args) {

        try {
            String s[] = FileUtil.loadFileReportToString("data/contacts.tsv");
            List<Contact> contacts = ContactHandler.createContactItems(s);
            Map<Integer, List<List<ErrorInfo<String, String>>>> integerMapMap = ContactValidator.contactsInvalid(contacts);
            /*integerMapMap.forEach((K,V)->{
                V.forEach(listErrorMapping->{
                    listErrorMapping.forEach(listErrorDetail->{
                        System.out.println(K+":"+listErrorDetail.toString());
                    });
                });
            });*/
       /*   FileUtil.storeFileReport("myOutput","valid-contacts.tab",ContactValidator.contactsValid(contacts));
            ContactValidator.contactsValid(contacts).forEach(contact -> {
                System.out.println(contact.toLine());
            });*/
            //  System.out.println(ContactValidator.contactsValid(contacts).size());

            ReportService<Contact> contactValid=new ContactValidReport();
            contactValid.generateReport("hii","valid-contact"+".tab",ContactValidator.contactsValid(contacts));
            Collections.sort(contacts, new SortUtil<>("state"));

            ReportService<Contact> contactReportService=new ContactPerStateReport();
            contactReportService.generateReport("hii","contact-per-state"+".tab",ContactValidator.contactsValid(contacts));

            ReportService<Contact> invalidContactService= new ContactInvalidDetailReport();
            invalidContactService.generateReport("hii","invalid-contact-details"+".tab",contacts);
            contacts.forEach(contact -> {
                // System.out.println(contact.toLine());
            });
            //  System.out.println(DatetimeUtil.calculatePreciseAge("31/10/1995"));

            for (String keys : ContactHandler.countContactPerAgeGroup(ContactValidator.contactsValid(contacts)).keySet()) {
                System.out.println( keys+"  "+ContactHandler.countContactPerAgeGroup(ContactValidator.contactsValid(contacts)).get(keys));
            }


        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        } catch (DateTimeParseException e) {
            LOGGER.info(e.getMessage());
        }

    }


    //Sort Contact by ZipCode


    //2: Validate Contact Data
    private static void addFieldError(Map<String, Integer> counts, String fieldName) {
        Integer count = counts.get(fieldName);
        if (count == null) {
            count = 0;
        }
        count = count + 1;
        counts.put(fieldName, count);
    }
}
