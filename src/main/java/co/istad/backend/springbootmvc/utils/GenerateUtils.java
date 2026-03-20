package co.istad.backend.springbootmvc.utils;

import java.util.concurrent.ThreadLocalRandom;

public class GenerateUtils {
    public static String randomProductCode() {
        String prefix = "ISTAD-PRO-";
        int randomNumber = ThreadLocalRandom.current().nextInt(0, 100000);
        return prefix + String.format("%03d", randomNumber);
    }
}
