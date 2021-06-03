package com.main.parser.strategy;

import com.main.util.StaxStreamProcessor;
import javax.xml.stream.XMLStreamException;
import java.time.LocalDate;

public class DoctorParsingStrategy extends ParsingStrategy {

    public DoctorParsingStrategy() {
        super();
    }

    @Override
    public void parse(StaxStreamProcessor streamProcessor) throws XMLStreamException {
        switch (streamProcessor.getLocalName()) {
            case "doctor":
                String id = streamProcessor.getAttribute("id");
                object.setId(id);
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
            case "category":
                String category = streamProcessor.getText();
                object.setCategory(category);
                break;
            case "experience":
                Short experience = Short.parseShort(streamProcessor.getText());
                object.setExperience(experience);
                break;
            case "vacation_start":
                LocalDate vacationStart = LocalDate.parse(streamProcessor.getText());
                object.setVacationStart(vacationStart);
                break;
            case "vacation_end":
                LocalDate vacationEnd = LocalDate.parse(streamProcessor.getText());
                object.setVacationEnd(vacationEnd);
                break;
        }
    }
}
