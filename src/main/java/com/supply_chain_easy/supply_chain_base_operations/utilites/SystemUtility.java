package com.supply_chain_easy.supply_chain_base_operations.utilites;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class SystemUtility {

    public static String generateId(String entityName) {

        return entityName.toUpperCase() + "-" +
                UUID.randomUUID().toString()
                        .substring(0, 8)
                        .toUpperCase();
    }

    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL = "@#$%&*!?";
    private static final String ALL = UPPER + LOWER + DIGITS + SPECIAL;
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateRandomPassword(int length) {
        if (length < 8) {
            throw new IllegalArgumentException("Password length must be at least 8.");
        }
        List<Character> password = new ArrayList<>();
        //Ensure at least one character from each category
        password.add(UPPER.charAt(RANDOM.nextInt(UPPER.length())));
        password.add(LOWER.charAt(RANDOM.nextInt(LOWER.length())));
        password.add(DIGITS.charAt(RANDOM.nextInt(DIGITS.length())));
        password.add(SPECIAL.charAt(RANDOM.nextInt(SPECIAL.length())));
        // Fill remaining characters
        for (int i = 4; i < length; i++) {
            password.add(ALL.charAt(RANDOM.nextInt(ALL.length())));
        }
        // Shuffle for randomness
        Collections.shuffle(password, RANDOM);
        StringBuilder sb = new StringBuilder();
        password.forEach(sb::append);

        return sb.toString();
    }
}
