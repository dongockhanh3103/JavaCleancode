package vn.kms.launch.cleancode.Report;

import vn.kms.launch.cleancode.Config.Annotation.ColunmField;
import vn.kms.launch.cleancode.Contact;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Logger;

public class ContactValidReport implements ReportService<Contact> {
    @Override
    public void generateReport(String fileDirectory, String fileName, List<Contact> datas) {
        File outputFile = new File(fileDirectory);
        if (!outputFile.exists()) {
            outputFile.mkdirs();
        }
        try (Writer outputPrinter = new FileWriter(new File(outputFile,fileName))){
            outputPrinter.write(getHeaderReport());
            if(!datas.isEmpty()){
                datas.forEach(contact -> {
                    try {
                        outputPrinter.write(contact.toLine()+ "\r\n");
                    } catch (IOException e) {
                        LOGGER.info(e.getMessage());
                    }
                });
                outputPrinter.flush();
            }
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }
    }

    @Override
    public String getHeaderReport() {
        String headerLine="";
        for (Field field : Contact.class.getDeclaredFields()) {
            field.setAccessible(true);
            ColunmField headerMapping= field.getAnnotation(ColunmField.class);
            if(headerMapping!=null){
                headerLine+=headerMapping.value()+"\t";
            }
        }
        return headerLine+ "\r\n";
    }
}
