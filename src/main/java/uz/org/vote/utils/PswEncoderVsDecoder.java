package uz.org.vote.utils;

import java.util.Base64;

public class PswEncoderVsDecoder {
    public static String DecodePsw(String encodedString){
        byte[] decode = Base64.getDecoder().decode(encodedString);
        return new String(decode);
    }

    public static String EncodePsw(String password){
        return Base64.getEncoder().encodeToString(password.getBytes());
    }

    public static String[] KeyDecoder(String key){
        byte[] decodedKey = Base64.getDecoder().decode(key);
        String adminKey = new String(decodedKey);
        String[] usernameVSpassword = adminKey.split("&");
        return usernameVSpassword;
    }
}
