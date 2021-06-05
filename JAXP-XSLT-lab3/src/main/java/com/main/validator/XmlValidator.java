package com.main.validator;

import com.main.util.StaxStreamProcessor;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class XmlValidator {
    private final static SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

    private final Schema schema;

    public XmlValidator(String path) throws SAXException {
        this.schema = factory.newSchema(new File(Objects
                .requireNonNull(getClass().getClassLoader().getResource(path)).getFile()));
    }

    public void validate(String path) throws XMLStreamException, IOException, SAXException {
        StaxStreamProcessor streamProcessor = new StaxStreamProcessor(getClass()
                .getClassLoader().getResourceAsStream(path));

        Validator validator = schema.newValidator();
        validator.validate(streamProcessor.getSource());
    }
}
