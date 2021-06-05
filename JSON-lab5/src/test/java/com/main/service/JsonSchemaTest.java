package com.main.service;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;


class JsonSchemaTest {
    private JSONObject correct;
    private JSONObject wrong;
    private Schema schema;

    @BeforeEach
    void setUp() {
        JSONObject jsonSchema = new JSONObject(new JSONTokener(Objects
                .requireNonNull(getClass().getClassLoader().getResourceAsStream("static/schema.json"))));
        correct = new JSONObject(new JSONTokener(Objects
                .requireNonNull(getClass().getClassLoader().getResourceAsStream("static/object.json"))));
        wrong = new JSONObject(new JSONTokener(Objects
                .requireNonNull(getClass().getClassLoader().getResourceAsStream("static/wrong.json"))));
        schema = SchemaLoader.load(jsonSchema);
    }

    @Test
    public void assertDoesNotThrowValidationException() {
        assertDoesNotThrow(() -> schema.validate(correct));
    }

    @Test
    public void assertThrowsWhenWrong() {
        ValidationException exception = assertThrows(ValidationException.class, () -> schema.validate(wrong));
        exception.getAllMessages().forEach(System.out::println);
    }
}