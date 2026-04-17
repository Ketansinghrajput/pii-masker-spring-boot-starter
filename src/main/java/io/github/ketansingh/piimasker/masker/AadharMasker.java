package io.github.ketansingh.piimasker.masker;

public class AadharMasker {
    public static String mask(String input) {
        if (input == null || input.isEmpty()) return input;

        String cleaned = input.replaceAll("\\s", "");

        if (cleaned.length() != 12) return input;

        return "XXXX-XXXX-" + cleaned.substring(8);
    }
}