package com.company.restaurant.Methods;


import java.util.Arrays;
import java.util.List;

public class StringConfigurerMethods {

    public static String replaceWhiteSpaceWithMinus(String word){
        return word.replaceAll("\\s+", "-");
    }
    public static List<String> allColorsinBootstrap(){
        return Arrays.asList("primary","success","danger","warning","info","secondary","warning","info");
    } 

}
