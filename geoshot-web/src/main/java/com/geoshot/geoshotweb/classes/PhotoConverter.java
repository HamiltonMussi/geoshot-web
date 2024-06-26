package com.geoshot.geoshotweb.classes;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.io.IOException;
import java.util.Calendar;
import org.apache.commons.io.FileUtils;
import com.geoshot.geoshotweb.classes.HashGeneretor;

public class PhotoConverter {

    public static String encoder(String filename) {
        byte[] fileContent = null;
        String encodedString = null;
        try {
            fileContent   = FileUtils.readFileToByteArray(new File(filename));
            encodedString = Base64.getEncoder().encodeToString(fileContent);
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
        return encodedString;
    }

    public  static  String encoder(byte[] imageBytes) {
        String encodedString = null;
        encodedString = Base64.getEncoder().encodeToString(imageBytes);
        return encodedString;
    }

    public static File decoder(String encodedString, String pathToSaveFile) {

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

        File photo = new File(String.format("%s/%s.png",pathToSaveFile,HashGeneretor.getHash("MD5",timeStamp)));

        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        try {
            FileUtils.writeByteArrayToFile(photo, decodedBytes);
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
        return photo;
    }

    public static File decoder(String encodedString) {
        return PhotoConverter.decoder(encodedString,".");
    }
}