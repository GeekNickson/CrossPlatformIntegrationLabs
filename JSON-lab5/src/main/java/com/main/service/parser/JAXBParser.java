package com.main.service.parser;

import com.main.model.xml.RootXml;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class JAXBParser {
    private final JAXBContext context;

    public JAXBParser() throws JAXBException {
        this.context = JAXBContext.newInstance(RootXml.class);
    }

    public RootXml toObject(InputStream inputStream) throws JAXBException {
        return (RootXml) context.createUnmarshaller().unmarshal(inputStream);
    }

    public void toXML(RootXml object, Path path) throws JAXBException, IOException {
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        OutputStream outputStream = Files.newOutputStream(path);
        marshaller.marshal(object, outputStream);
    }
}
