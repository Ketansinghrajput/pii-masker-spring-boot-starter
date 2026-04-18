package io.github.ketansingh.piimasker.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.github.ketansingh.piimasker.annotation.MaskPII;
import io.github.ketansingh.piimasker.annotation.PiiType;
import io.github.ketansingh.piimasker.masker.PiiMaskingUtil;

import java.io.IOException;

public class PiiMaskingSerializer extends StdSerializer<String> implements ContextualSerializer {

    private PiiType piiType;

    public PiiMaskingSerializer() {
        super(String.class);
    }

    public PiiMaskingSerializer(PiiType piiType) {
        super(String.class);
        this.piiType = piiType;
    }

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider prov) throws IOException {
        if (value == null) {
            gen.writeNull();
            return;
        }

        if (piiType != null) {
            gen.writeString(PiiMaskingUtil.maskByType(value, piiType));
        } else {
            gen.writeString(PiiMaskingUtil.maskAll(value));
        }
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        if (property != null) {
            MaskPII annotation = property.getAnnotation(MaskPII.class);
            if (annotation != null) {
                return new PiiMaskingSerializer(annotation.type());
            }
        }
        return prov.findValueSerializer(String.class, property);
    }
}