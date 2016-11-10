package hu.dpc.edu.rest;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Arrays;

/**
 * Created by vrg on 2016. 11. 10..
 */

@Provider
public class MessageToXMLWriter implements MessageBodyWriter<Message> {

    private String rootElement;

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        System.out.println(Arrays.toString(annotations));

        boolean customMarshaller = false;

        for (Annotation annotation : annotations) {
            if (annotation instanceof CustomMarshaller) {
                customMarshaller = true;
                rootElement = ((CustomMarshaller)annotation).rootElement();
                break;
            }
        }

        return Message.class.isAssignableFrom(type)
                && mediaType.isCompatible(MediaType.APPLICATION_XML_TYPE)
                && customMarshaller;
    }

    @Override
    public long getSize(Message message, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return 0;
    }

    @Override
    public void writeTo(Message message, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        try {
            final XMLStreamWriter out = XMLOutputFactory.newFactory()
                    .createXMLStreamWriter(entityStream);

            out.writeStartElement(rootElement);
            out.writeStartElement("code");
            out.writeCharacters(String.valueOf(message.getCode()));
            out.writeEndElement();
            out.writeStartElement("uzenet");
            out.writeCharacters(message.getMessage());
            out.writeEndElement();
            out.writeStartElement("displayName");
            out.writeCharacters(message.getDisplayName());
            out.writeEndElement();
            out.writeEndElement();
            out.close();

        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
    }
}
