package com.company.restaurant.Methods;

import java.util.Random;

public class PINGenerator {
    public static String generate() {
        Random random = new Random();
        return String.format("%04d", random.nextInt(10000));
    }
}
