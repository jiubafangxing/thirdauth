package www.thirdauth.com.thirdparty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.lang.reflect.Type;
import java.util.Collections;
import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import feign.Util;

public class ThirdAuthJacksonEncoder implements Encoder {

    private final ObjectMapper mapper;

    public ThirdAuthJacksonEncoder() {
        this(Collections.<Module>emptyList());
    }

    public ThirdAuthJacksonEncoder(Iterable<Module> modules) {
        this(new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .configure(SerializationFeature.INDENT_OUTPUT, true)
                .registerModules(modules));
    }

    public ThirdAuthJacksonEncoder(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void encode(Object object, Type bodyType, RequestTemplate template) {
        try {
            JavaType javaType = mapper.getTypeFactory().constructType(bodyType);
            template.body(mapper.writerFor(javaType).writeValueAsBytes(object), Util.UTF_8);
        } catch (JsonProcessingException e) {
            throw new EncodeException(e.getMessage(), e);
        }
    }
}