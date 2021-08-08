package utils;

import java.util.Random;

public class Defaults {
    public static final String BASE_URL = System.getProperty("url");
    public static final String EMAIL =  System.getProperty("email");
    public static final String PASSWORD = System.getProperty("password");
    public static final String BROWSER = System.getProperty("browser");

    public  static String generateRandomString(){
        Random random = new Random();
        String generatedString = random.ints(98 ,122 )
            .limit(8)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();
        return generatedString;

}}
