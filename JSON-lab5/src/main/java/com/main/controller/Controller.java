package com.main.controller;

import com.main.service.FileService;
import com.main.service.exceptions.FileFormatException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class Controller {
    private final FileService fileService;

    @PostMapping("/json")
    public ResponseEntity<String> getJSON(@RequestBody MultipartFile file) throws IOException, JAXBException,
            FileFormatException {
        return ResponseEntity.ok().body(fileService.getJSON(file));
    }

    @GetMapping("/json")
    public void downloadJSON(HttpServletResponse response) throws IOException {
        fileService.downloadJSON(response);
    }
}
