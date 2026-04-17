package io.github.ketansingh.piimasker.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.github.ketansingh.piimasker.annotation.MaskPII;
import io.github.ketansingh.piimasker.masker.AadharMasker;

import java.io.IOException;

public class PiiMaskingSerializer extends StdSerializer<String> implements ContextualSerializer {

    public PiiMaskingSerializer() {
        super(String.class);
    }

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider prov) throws IOException {
        gen.writeString(AadharMasker.mask(value));
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        if (property != null && property.getAnnotation(MaskPII.class) != null) {
            return this;
        }

        return prov.findValueSerializer(property.getType(), property);
    }
}