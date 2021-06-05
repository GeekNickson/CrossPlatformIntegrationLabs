package com.main.service.parser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class JAXBParser<T> {
    private final JAXBContext context;
    private final Class<T> className;

    public JAXBParser(Class<T> className) throws JAXBException {
        this.className = className;
        this.context = JAXBContext.newInstance(className);
    }

    public T toObject(InputStream inputStream) throws JAXBException {
        return className.cast(context.createUnmarshaller().unmarshal(inputStream));
    }

    public void toXML(T object, Path path) throws JAXBException, IOException {
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        OutputStream outputStream = Files.newOutputStream(path);
        marshaller.marshal(object, outputStream);
    }
}
