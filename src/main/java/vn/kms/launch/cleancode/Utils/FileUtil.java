package vn.kms.launch.cleancode.Utils;

import vn.kms.launch.cleancode.Config.Annotation.ColunmField;
import vn.kms.launch.cleancode.Constants.Constants;
import vn.kms.launch.cleancode.Contact;
import vn.kms.launch.cleancode.common.Common;

import java.io.*;
import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Logger;

public class FileUtil {
    private static Logger LOGGER = Logger.getLogger("InfoLogging");
    public static String[] loadFileReportToString(String filePath) throws IOException {
        try (InputStream fileInputStream = new FileInputStream(filePath)) {
            char[] buff = new char[Constants.MAX_CHARACTER_FILE_SIZE];
            int oneCharacterRead;
            int countCharacter = 0;
            while ((oneCharacterRead = fileInputStream.read()) != -1) {
                buff[countCharacter] = (char) oneCharacterRead;
                countCharacter++;
            }
            return convertCharArrayToStringArray(buff, countCharacter);
        }
    }
    private static String[] convertCharArrayToStringArray(char[] chars, int sizeCharacterOfFile) {
        return new String(chars, 0, sizeCharacterOfFile).split("\r"); // all data from file load to string s
    }


}
