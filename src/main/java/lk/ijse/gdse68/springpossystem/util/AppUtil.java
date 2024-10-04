package lk.ijse.gdse68.springpossystem.util;

import java.util.UUID;
/**
 * @author : sachini
 * @date : 2024-10-04
 **/
public class AppUtil {
    //TODO :set ID formate
    public static String createCustomerId(){
        return "CUS-"+ UUID.randomUUID();
    }
}
