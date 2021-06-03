package com.main.parser.strategy;

import com.main.util.StaxStreamProcessor;

import javax.xml.stream.XMLStreamException;

public class SpecialtyParsingStrategy extends ParsingStrategy {

    public SpecialtyParsingStrategy() {
        super();
    }

    @Override
    public void parse(StaxStreamProcessor streamProcessor) throws XMLStreamException {
        switch (streamProcessor.getLocalName()) {
            case "specialty":
                String id = streamProcessor.getAttribute("id");
                object.setId(id);
                String doctorId = streamProcessor.getAttribute("doctor_ref");
                object.setDoctorId(doctorId);
                break;
            case "name":
                String name = streamProcessor.getText();
                object.setName(name);
                break;
        }
    }
}
