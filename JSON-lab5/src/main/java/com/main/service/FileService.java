package com.main.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.constants.FileTypes;
import com.main.mapper.XmlObjectMapper;
import com.main.model.xml.RootXml;
import com.main.service.dto.DoctorsDto;
import com.main.service.exceptions.FileFormatException;
import com.main.service.parser.JAXBParser;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FileService {
    private final JAXBParser parser;
    private final ObjectMapper objectMapper;
    private final XmlObjectMapper xmlObjectMapper;
    private final DoctorService doctorService;

    public String getJSON(MultipartFile file) throws IOException, JAXBException, FileFormatException {
        if (!Objects.requireNonNull(file.getContentType()).equals(FileTypes.XML)) {
            throw new FileFormatException("Wrong file format");
        }

        doctorService.deleteAll();

        RootXml root = parser.toObject(file.getInputStream());

        root.getDoctors().getDoctor()
                .stream()
                .map(xmlObjectMapper::toDoctor)
                .forEach(doctorService::save);

        return  objectMapper.writeValueAsString(doctorService.findAll());
    }

    public void downloadJSON(HttpServletResponse response) throws IOException {
        DoctorsDto doctorsDto = new DoctorsDto(doctorService.findAll());
        String json = objectMapper.writeValueAsString(doctorsDto);

        InputStream inputStream = new ByteArrayInputStream(json.getBytes());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setContentLength(json.getBytes().length);
        response.setHeader("Content-Disposition", "attachment; filename=\"file.json\"");

        IOUtils.copyLarge(inputStream, response.getOutputStream());

        inputStream.close();
        response.flushBuffer();
    }

}
