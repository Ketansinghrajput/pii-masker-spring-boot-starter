package io.github.ketansingh.piimasker.config;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.github.ketansingh.piimasker.jackson.PiiMaskingSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PiiMaskerAutoConfiguration {


    @Bean
    public Module piiMaskingModule() {
        SimpleModule module = new SimpleModule("PiiMaskingModule");

        return module;
    }
}