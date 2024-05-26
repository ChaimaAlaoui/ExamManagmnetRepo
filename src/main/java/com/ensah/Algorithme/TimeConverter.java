package com.ensah.Algorithme;

public class TimeConverter {
    

    public static float convertTimeToFloat(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        
        // Convert to float format, hours and minutes as a decimal
        float floatTime = hours + minutes / 100.0f;

        return floatTime;
    }
}
