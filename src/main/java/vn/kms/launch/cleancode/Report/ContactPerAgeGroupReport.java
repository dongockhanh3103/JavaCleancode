package vn.kms.launch.cleancode.Report;

import vn.kms.launch.cleancode.Constants.Constants;
import vn.kms.launch.cleancode.Contact;
import vn.kms.launch.cleancode.Utils.DatetimeUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactPerAgeGroupReport implements ReportService<Contact> {
    @Override
    public void generateReport(String fileDirectory, String fileName, List<Contact> data) {
        File outputFile = new File(fileDirectory);
        if (!outputFile.exists()) {
            outputFile.mkdirs();
        }
        try (Writer outputPrinter = new FileWriter(new File(outputFile,fileName))){
            outputPrinter.write(getHeaderReport());

            String[] ageOfGroups = { "Children", "Adolescent", "Adult", "Middle Age", "Senior" };
            for (String ageOfGroup : ageOfGroups) {
                outputPrinter.write(ageOfGroup +"\t"+ contactPerAgeGroupSummary(data).get(ageOfGroup));
            }

        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }
    }



    @Override
    public String getHeaderReport() {
        return Constants.getReportHeaderItem(Constants.CONTACT_PER_AGE_GROUP_FILE_NAME)+"\r\n";
    }

    public static double calculateToPercentObject (double count,double total){
        return Math.round(count/total*100*10)/10;
    }

    public static Map<String,String> contactPerAgeGroupSummary(List<Contact> contactItems){
        Map<String,String> contactPerAgeGroupMapping =new HashMap<>();
        Map<String,Integer> countContactPerAge= countContactPerAge(contactItems);
        double totalItems=contactItems.size();
        countContactPerAge.forEach((group,number)->{
            contactPerAgeGroupMapping.put(group,number+"\t"+calculateToPercentObject(number,totalItems)+"%"+"\r\n");
        });

        return contactPerAgeGroupMapping;
    }

    private static Map<String,Integer> countContactPerAge(List<Contact> contactItems){
        Map<String, Integer> contactPerAgeGroupMapping = new HashMap<>();
        setAgeContact(contactItems);
        contactItems.forEach(contact -> {
            int ageGroupCount = 0;
            if (contactPerAgeGroupMapping.containsKey(DatetimeUtil.calculateAgeGroup(contact.getAge()))) {
                ageGroupCount = contactPerAgeGroupMapping.get(DatetimeUtil.calculateAgeGroup(contact.getAge()));
            }
            contactPerAgeGroupMapping.put(DatetimeUtil.calculateAgeGroup(contact.getAge()), ageGroupCount + 1);
        });
        return contactPerAgeGroupMapping;

    }
    private static void setAgeContact(List<Contact> contactItems){
        contactItems.forEach(contact -> {
            contact.setAge(DatetimeUtil.calculatePreciseAge(contact.getDayOfBirth()));
        });
    }

}
