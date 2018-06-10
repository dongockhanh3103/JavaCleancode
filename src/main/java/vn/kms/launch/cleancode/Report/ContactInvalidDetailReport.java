package vn.kms.launch.cleancode.Report;

import vn.kms.launch.cleancode.Constants.Constants;
import vn.kms.launch.cleancode.Contact;
import vn.kms.launch.cleancode.Handler.ContactValidator;
import vn.kms.launch.cleancode.common.ErrorInfo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactInvalidDetailReport implements ReportService<Contact> {
    @Override
    public void generateReport(String fileDirectory, String fileName, List<Contact> data) {
        File outputFile = new File(fileDirectory);
        if (!outputFile.exists()) {
            outputFile.mkdirs();
        }
        try (Writer outputPrinter = new FileWriter(new File(outputFile,fileName))){
            outputPrinter.write(getHeaderReport());
            getContactInvalidDetail(data).forEach(invalidContact->{
                try {
                    outputPrinter.write(invalidContact+ "\r\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }

    }

    @Override
    public String getHeaderReport() {
        return Constants.getReportHeaderItem("invalid-contact-details")+"\r\n";
    }

    private List<String> getContactInvalidDetail(List<Contact> contactItems){
        List<String> invalidContactDetailMapping=new ArrayList<>();
        ContactValidator.contactsInvalid(contactItems).forEach((K,V)->{
            V.forEach(listErrorMapping->{

                listErrorMapping.forEach(listErrorDetail->{
                    System.out.println(K+"\t"+listErrorDetail.toString());
                    invalidContactDetailMapping.add(K+"\t"+listErrorDetail.toString());
                });
            });
        });


        return invalidContactDetailMapping;
    }
}
