package config;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public final class KafkaTopics {

    public static final String CALC_REQ = "calc_request";
    public static final String CALC_RES = "cal_response";

    public static List<String> getTopicsStringList() {
        var list = new ArrayList<String>();
        Field[] arr = KafkaTopics.class.getFields();
        for (Field f : arr) {
            if (f.getType().equals(String.class)) { // check if field is a String
                String s = null; // get value of each field
                try {
                    s = (String)f.get(null);
                    list.add(s);
                } catch (IllegalAccessException e) {
                }

            }

        }
        return list;
    }
}
