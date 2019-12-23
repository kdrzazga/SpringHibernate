package org.kd.main.client.viewfx.lib;

class DigitConverter {

    public static String removeExtraDots(String number){
        int dotIndex = number.indexOf((int) '.');
        
        if (dotIndex != number.lastIndexOf(".")) {
            int secondDotIndex = number.substring(dotIndex + 1).indexOf('.') + dotIndex;
            number = number.substring(0, secondDotIndex + 1);
        }

        return number;
    }
    
}
