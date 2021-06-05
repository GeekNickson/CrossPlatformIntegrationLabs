package com.main.util;

import lombok.Getter;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stax.StAXSource;
import java.io.InputStream;

public class StaxStreamProcessor {
    private static final XMLInputFactory factory = XMLInputFactory.newInstance();

    @Getter
    private final XMLStreamReader reader;

    public StaxStreamProcessor(InputStream inputStream) throws XMLStreamException {
        this.reader = factory.createXMLStreamReader(inputStream);
    }

    public StAXSource getSource() {
        return new StAXSource(reader);
    }
}
