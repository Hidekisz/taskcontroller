package br.com.vini.taskcontroller.util;

import java.time.LocalDate;

public class FUUtil {

    public static String montaData(LocalDate localDateTime) {
        if (localDateTime != null){
            StringBuilder data = new StringBuilder();
            data.append(localDateTime.getDayOfMonth()).append("-");
            data.append(localDateTime.getMonth()).append("-");
            data.append(localDateTime.getYear());

            return data.toString();
        }
        return null;
    }
}
