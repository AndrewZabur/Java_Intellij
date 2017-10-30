package lab2.serialize;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lab2.Garage;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;

public class SerializeGarageJson implements Serializing<Garage> {

    @Override
    public void serializingObj(Garage obj, Writer out) throws IOException {

        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        mapper.writeValue(out, obj);

    }

    @Override
    public Garage deserializingObj(Reader in) throws IOException {
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        return mapper.readValue(in, Garage.class);
    }

}
