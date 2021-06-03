package com.main;

import com.main.parser.XmlParser;
import com.main.service.creator.HtmlCreator;
import com.main.service.linker.DoctorAttributesLinker;
import com.main.service.validator.XmlValidator;
import com.main.storage.Storage;
import org.xml.sax.SAXException;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class App {

    public static void main(String[] args) {
        try {
            XmlValidator validator = new XmlValidator("schema.xsd");
            validator.validate("doc.xml");
            XmlParser parser = new XmlParser("doc.xml");
            parser.parse();
            DoctorAttributesLinker linker = new DoctorAttributesLinker();
            linker.linkAll(Storage.getInstance().getDoctors());
            HtmlCreator.createDoctorsView();
        } catch (XMLStreamException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }
}
