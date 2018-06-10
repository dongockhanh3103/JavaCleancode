package vn.kms.launch.cleancode.Report;

import java.util.List;
import java.util.logging.Logger;

public interface ReportService<T> {
    Logger LOGGER = Logger.getLogger("InfoLogging");
    void generateReport(String fileDirectory, String fileName,List<T> data );
    String getHeaderReport();
}
