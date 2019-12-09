package org.kd.main.server.model.operation;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class LogOperation {

    public String createEmptyLogFile(String filepath) {
        try {
            var file = new File(filepath + createTimestamp() + ".txt");

            if (file.createNewFile()) {
                Logger.getLogger(LogOperation.class.getSimpleName()).log(Level.INFO, "Created file " + file.getAbsolutePath());
                return file.getAbsolutePath();
            }
            else return null;

        } catch (IOException | NullPointerException e) {
            Logger.getLogger(LogOperation.class.getSimpleName()).log(Level.SEVERE, e.getMessage());
            System.err.println(e.getMessage());
            return "";
        }
    }

    public boolean deleteFile(String filepath) {

        var file = new File(filepath);
        return file.delete();
    }

    private String createTimestamp() {
        var now = Calendar.getInstance().getTime();
        var formatter = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss.SSSS");
        return formatter.format(now);
    }
}
