package com.main.parser.strategy;

import com.main.dto.XMLSchemaObjectDTO;
import com.main.util.StaxStreamProcessor;
import lombok.Getter;

import javax.xml.stream.XMLStreamException;

public abstract class ParsingStrategy {
    @Getter
    protected XMLSchemaObjectDTO object;

    protected ParsingStrategy() {
        this.object = new XMLSchemaObjectDTO();
    }

    public abstract void parse(StaxStreamProcessor streamProcessor) throws XMLStreamException;

    public void resetObject() {
        this.object = new XMLSchemaObjectDTO();
    }
}
