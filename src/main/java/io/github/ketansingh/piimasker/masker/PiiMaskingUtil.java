package io.github.ketansingh.piimasker.masker;

import io.github.ketansingh.piimasker.annotation.PiiType;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PiiMaskingUtil {

    private static final String AADHAAR_PATTERN = "\\b\\d{4}[\\s]?\\d{4}[\\s]?(\\d{4})\\b";
    private static final String CC_PATTERN = "\\b\\d{4}[-\\s]?\\d{4}[-\\s]?\\d{4}[-\\s]?(\\d{4})\\b";
    private static final String PHONE_PATTERN = "\\b[6-9]\\d{5}(\\d{4})\\b";

    private static final String EMAIL_PATTERN = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b";

    public static String maskAll(String text) {
        if (text == null || text.isEmpty()) return text;

        if (text.contains("**********")) return text;

        String masked = text;
        masked = masked.replaceAll(AADHAAR_PATTERN, "XXXX-XXXX-$1");
        masked = masked.replaceAll(CC_PATTERN, "XXXX-XXXX-XXXX-$1");
        masked = masked.replaceAll(PHONE_PATTERN, "XXXXXX$1");

        return maskEmailAtomic(masked);
    }

    private static String maskEmailAtomic(String text) {
        Matcher matcher = Pattern.compile(EMAIL_PATTERN).matcher(text);
        StringBuilder sb = new StringBuilder();
        int lastEnd = 0;

        while (matcher.find()) {
            sb.append(text, lastEnd, matcher.start());
            String email = matcher.group();

            if (email.contains("*")) {
                sb.append(email);
            } else {
                String[] parts = email.split("@");
                sb.append(parts[0].charAt(0)).append("****@").append(parts[1]);
            }
            lastEnd = matcher.end();
        }
        sb.append(text.substring(lastEnd));
        return sb.toString();
    }

    public static String maskByType(String text, PiiType type) {
        if (text == null || text.isEmpty() || type == null) return text;
        switch (type) {
            case AADHAAR: return text.replaceAll(AADHAAR_PATTERN, "XXXX-XXXX-$1");
            case CREDIT_CARD: return text.replaceAll(CC_PATTERN, "XXXX-XXXX-XXXX-$1");
            case PHONE: return text.replaceAll(PHONE_PATTERN, "XXXXXX$1");
            case EMAIL: return maskEmailAtomic(text);
            default: return text;
        }
    }
}