package com.main;


import com.main.mapper.XmlObjectMapper;
import com.main.model.xml.RootXml;
import com.main.service.creator.HtmlCreator;
import com.main.service.parser.JAXBParser;
import com.main.storage.Storage;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;

public class App {
    public static void main(String[] args) {
        try {
            JAXBParser<RootXml> parser = new JAXBParser<>(RootXml.class);
            RootXml root = parser.toObject(App.class
                    .getClassLoader().getResourceAsStream("doc.xml"));

            root.getServices().getService()
                    .forEach(serviceXml -> serviceXml.setPrice(serviceXml.getPrice()
                            .multiply(new BigDecimal("2.0"))));

            parser.toXML(root, Paths.get("doc_double_price.xml"));

            root.getDoctors().getDoctor()
                    .stream()
                    .map(XmlObjectMapper::toDoctor)
                        .forEach(doctor -> Storage.getInstance().getDoctors().add(doctor));

            HtmlCreator.createDoctorsView();
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }
}
