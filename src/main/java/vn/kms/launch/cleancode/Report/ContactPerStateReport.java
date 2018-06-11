package vn.kms.launch.cleancode.Report;

import vn.kms.launch.cleancode.Constants.Constants;
import vn.kms.launch.cleancode.Contact;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ContactPerStateReport implements ReportService<Contact> {
    @Override
    public void generateReport(String fileDirectory, String fileName, List<Contact> data) {
        File outputFile = new File(fileDirectory);
        if (!outputFile.exists()) {
            outputFile.mkdirs();
        }
        try (Writer outputPrinter = new FileWriter(new File(outputFile,fileName))){
            outputPrinter.write(getHeaderReport());
            countContactPerState(data).forEach((state,count)->{
                try {
                    outputPrinter.write(state+"\t"+count+ "\r\n");
                } catch (IOException e) {
                    LOGGER.info(e.getMessage());
                }

            });
        } catch (IOException e) {
           LOGGER.info(e.getMessage());
        }
    }

    @Override
    public String getHeaderReport() {
        return Constants.getReportHeaderItem(Constants.CONTACT_PER_STATE_FILE_NAME)+"\r\n";
    }
    public static Map<String, Integer> countContactPerState(List<Contact> contacts) {
        Map<String, Integer> contactPerStateMapping = new TreeMap<>();
        contacts.forEach(contact -> {
            int state_count = 0;
            if (contactPerStateMapping.containsKey(contact.getState())) {
                state_count = contactPerStateMapping.get(contact.getState());
            }
            contactPerStateMapping.put(contact.getState(), state_count + 1);
        });
        return contactPerStateMapping;
    }

}
