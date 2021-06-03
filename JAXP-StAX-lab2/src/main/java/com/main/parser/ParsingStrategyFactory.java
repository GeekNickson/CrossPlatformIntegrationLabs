package com.main.parser;

import com.main.constants.TagConstants;
import com.main.parser.strategy.*;

public class ParsingStrategyFactory {
    private static final ParsingStrategyFactory factory = new ParsingStrategyFactory();

    public static ParsingStrategyFactory getInstance() {
        return factory;
    }

    public ParsingStrategy getParsingStrategy(String tagName) {
        switch (tagName) {
            case TagConstants.DOCTOR:
                return new DoctorParsingStrategy();
            case TagConstants.PATIENT:
                return new PatientParsingStrategy();
            case TagConstants.SERVICE:
                return new ServiceParsingStrategy();
            case TagConstants.SPECIALTY:
                return new SpecialtyParsingStrategy();
            default:
                throw new IllegalArgumentException("Invalid tag name");
        }
    }
}
