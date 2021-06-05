package com.main.transformer;

import net.sf.saxon.TransformerFactoryImpl;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class XmlTransformer {
    private static final TransformerFactory factory = new TransformerFactoryImpl();

    private final Transformer transformer;

    public XmlTransformer(String templatePath) throws TransformerConfigurationException {
        this.transformer = factory.newTransformer(new StreamSource(getClass()
                .getClassLoader().getResourceAsStream(templatePath)));
    }

    public void transformAndSave(Source source, Path savePath) throws IOException, TransformerException {
        OutputStream outputStream = Files.newOutputStream(savePath);
        StreamResult result = new StreamResult(outputStream);
        transformer.transform(source, result);
    }
}
