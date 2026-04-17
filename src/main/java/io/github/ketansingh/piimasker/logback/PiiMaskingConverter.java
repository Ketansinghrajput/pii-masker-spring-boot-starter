package io.github.ketansingh.piimasker.logback;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class PiiMaskingConverter extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent event) {
        String message = event.getFormattedMessage();
        return message.replaceAll("\\b\\d{8}(\\d{4})\\b", "XXXX-XXXX-$1");
    }
}