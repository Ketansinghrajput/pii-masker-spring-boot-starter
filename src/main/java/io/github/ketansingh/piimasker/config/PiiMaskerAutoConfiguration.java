package io.github.ketansingh.piimasker.config;

import ch.qos.logback.classic.LoggerContext;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.github.ketansingh.piimasker.logback.PiiMaskingTurboFilter;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PiiMaskerAutoConfiguration implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
            PiiMaskingTurboFilter filter = new PiiMaskingTurboFilter();
            filter.setContext(context);
            filter.setName("PII_MASKER_FILTER");
            filter.start();

            context.getTurboFilterList().add(0, filter);
            System.out.println("[PII-MASKER] Logback TurboFilter Registered Successfully!");
        } catch (Exception e) {
            System.err.println("[PII-MASKER] Failed to register Logback filter: " + e.getMessage());
        }
    }

    @Bean
    public Module piiMaskingModule() {
        return new SimpleModule();
    }
}