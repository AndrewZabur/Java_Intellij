package lab2.serialize;

import com.thoughtworks.xstream.XStream;
import lab2.Garage;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class SerializeGarageXml implements Serializing<Garage> {

    @Override
    public void serializingObj(Garage obj, Writer out) throws IOException {
        XStream xs = new XStream();
        xs.toXML(obj, out);
    }

    @Override
    public Garage deserializingObj(Reader in) throws IOException {
        XStream xs = new XStream();
        Garage garage = new Garage();
        xs.fromXML(in, garage);
        return garage;
    }
}
