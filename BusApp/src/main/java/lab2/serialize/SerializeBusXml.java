package lab2.serialize;

import com.thoughtworks.xstream.XStream;
import lab2.Bus;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;


public class SerializeBusXml implements Serializing<Bus> {

    @Override
    public void serializingObj(Bus obj, Writer out) throws IOException{
        XStream xs = new XStream();
        xs.toXML(obj, out);
    }

    @Override
    public Bus deserializingObj(Reader in) throws IOException {
        XStream xs = new XStream();
        Bus bus = new Bus();
        xs.fromXML(in, bus);
        return bus;
    }
}
