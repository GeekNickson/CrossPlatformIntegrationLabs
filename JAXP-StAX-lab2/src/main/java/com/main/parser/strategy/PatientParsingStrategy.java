package com.main.parser.strategy;

import com.main.util.StaxStreamProcessor;

import javax.xml.stream.XMLStreamException;
import java.time.LocalDate;

public class PatientParsingStrategy extends ParsingStrategy {

    public PatientParsingStrategy() {
        super();
    }

    @Override
    public void parse(StaxStreamProcessor streamProcessor) throws XMLStreamException {
        switch (streamProcessor.getLocalName()) {
            case "patient":
                String id = streamProcessor.getAttribute("id");
                object.setId(id);
                Boolean insured = Boolean.parseBoolean(streamProcessor.getAttribute("insured"));
                object.setInsured(insured);
                break;
            case "first_name":
                String firstName = streamProcessor.getText();
                object.setFirstName(firstName);
                break;
            case "last_name":
                String lastName = streamProcessor.getText();
                object.setLastName(lastName);
                break;
            case "age":
                Short age = Short.parseShort(streamProcessor.getText());
                object.setAge(age);
                break;
        }
    }
}
