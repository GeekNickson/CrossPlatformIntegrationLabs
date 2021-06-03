package com.main.util;

import lombok.Getter;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;

public class StaxStreamProcessor implements AutoCloseable {
    private static final XMLInputFactory FACTORY = XMLInputFactory.newInstance();

    @Getter
    private final XMLStreamReader reader;

    @Getter
    private int currentEvent;

    public StaxStreamProcessor(InputStream inputStream) throws XMLStreamException {
        this.reader = FACTORY.createXMLStreamReader(inputStream);
    }

    public boolean insideParent(String parent) throws XMLStreamException {
        if (reader.hasNext()) {
            currentEvent = reader.next();
            return parent == null || currentEvent != XMLEvent.END_ELEMENT ||
                    !parent.equals(reader.getLocalName());
        }
        return false;
    }

    public String getAttribute(String name) throws XMLStreamException {
        return reader.getAttributeValue(null, name);
    }
    public String getText() throws XMLStreamException {
        return reader.getElementText();
    }

    public String getLocalName() {
        return reader.getLocalName();
    }

    public boolean isOpeningElement() {
        return currentEvent == XMLEvent.START_ELEMENT;
    }
    public boolean isClosingElement(String element) {
        return currentEvent == XMLEvent.END_ELEMENT && element.equals(reader.getLocalName());
    }

    @Override
    public void close() throws XMLStreamException {
        if (reader != null) {
            reader.close();
        }
    }
}
