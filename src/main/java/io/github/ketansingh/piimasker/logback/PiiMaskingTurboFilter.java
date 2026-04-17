package io.github.ketansingh.piimasker.logback;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.turbo.TurboFilter;
import ch.qos.logback.core.spi.FilterReply;
import io.github.ketansingh.piimasker.masker.AadharMasker;
import org.slf4j.Marker;

public class PiiMaskingTurboFilter extends TurboFilter {

    @Override
    public FilterReply decide(Marker marker, Logger logger, Level level, String format, Object[] params, Throwable t) {
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                if (params[i] instanceof String) {
                    params[i] = AadharMasker.mask((String) params[i]);
                }
            }
        }
        return FilterReply.NEUTRAL;
    }
}