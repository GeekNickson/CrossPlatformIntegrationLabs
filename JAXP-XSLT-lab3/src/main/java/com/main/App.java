package com.main;


import com.main.transformer.XmlTransformer;
import com.main.util.StaxStreamProcessor;
import com.main.validator.XmlValidator;
import org.xml.sax.SAXException;

import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App {
    public static void main(String[] args)  {
        try {
            XmlValidator validator = new XmlValidator("schema.xsd");
            validator.validate("doc.xml");
            StaxStreamProcessor streamProcessor = new StaxStreamProcessor(App.class
                    .getClassLoader().getResourceAsStream("doc.xml"));
            XmlTransformer transformer = new XmlTransformer("template.xslt");
            Path savePath = Paths.get("doctor.html");
            transformer.transformAndSave(streamProcessor.getSource(), savePath);
        } catch (SAXException | XMLStreamException | IOException | TransformerException e) {
            e.printStackTrace();
        }
    }
}
