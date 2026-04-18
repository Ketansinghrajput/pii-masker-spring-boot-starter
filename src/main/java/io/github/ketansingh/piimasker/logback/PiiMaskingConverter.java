package io.github.ketansingh.piimasker.logback;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import io.github.ketansingh.piimasker.masker.PiiMaskingUtil;

public class PiiMaskingConverter extends ClassicConverter {

    private static final ThreadLocal<Boolean> IS_PROCESSING = ThreadLocal.withInitial(() -> false);

    @Override
    public String convert(ILoggingEvent event) {
        if (event == null || event.getFormattedMessage() == null) {
            return "";
        }

        if (IS_PROCESSING.get()) {
            return event.getFormattedMessage();
        }

        try {
            IS_PROCESSING.set(true);
            return PiiMaskingUtil.maskAll(event.getFormattedMessage());
        } finally {
            IS_PROCESSING.remove();
        }
    }
}
//converter