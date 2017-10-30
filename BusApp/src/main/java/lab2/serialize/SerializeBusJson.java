package lab2.serialize;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lab2.Bus;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;


public class SerializeBusJson implements Serializing<Bus> {

    @Override
    public void serializingObj(Bus obj, Writer out) throws IOException {
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        mapper.writeValue(out , obj);
    }

    @Override
    public Bus deserializingObj(Reader in) throws IOException {
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        return mapper.readValue(in, Bus.class);
    }
}
