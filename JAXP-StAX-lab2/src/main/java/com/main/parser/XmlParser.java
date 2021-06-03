package com.main.parser;

import com.main.constants.TagConstants;
import com.main.dto.XMLSchemaObjectDTO;
import com.main.parser.strategy.ParsingStrategy;
import com.main.storage.Storage;
import com.main.util.StaxStreamProcessor;

import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;
import java.util.List;


public class XmlParser {
    private final String path;

    public XmlParser(String path)  {
        this.path = path;
    }

    public void parse() throws XMLStreamException {
        saveObjectsFromXML(TagConstants.DOCTORS, TagConstants.DOCTOR);
        saveObjectsFromXML(TagConstants.PATIENTS, TagConstants.PATIENT);
        saveObjectsFromXML(TagConstants.SERVICES, TagConstants.SERVICE);
        saveObjectsFromXML(TagConstants.SPECIALTIES, TagConstants.SPECIALTY);
     }

     private void saveObjectsFromXML(String parentTag, String tagName) throws XMLStreamException {
         StaxStreamProcessor streamProcessor = new StaxStreamProcessor(getClass()
                 .getClassLoader().getResourceAsStream(path));

        List<XMLSchemaObjectDTO> objects = new ArrayList<>();

        ParsingStrategy strategy = ParsingStrategyFactory.getInstance().getParsingStrategy(tagName);

        while (streamProcessor.insideParent(parentTag)) {
            if (streamProcessor.isClosingElement(tagName)) {
                objects.add(strategy.getObject());
                strategy.resetObject();
            }
            if (streamProcessor.isOpeningElement()) {
                strategy.parse(streamProcessor);
            }
        }

        objects.forEach(object -> Storage.getInstance().save(object, tagName));
        streamProcessor.close();
     }
}
