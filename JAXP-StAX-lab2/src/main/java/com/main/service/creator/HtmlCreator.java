package com.main.service.creator;

import com.main.model.Doctor;
import com.main.util.HtmlTemplate;
import com.main.storage.Storage;
import htmlflow.DynamicHtml;
import htmlflow.HtmlView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class HtmlCreator {
    private static final HtmlView<Stream<Doctor>> template = DynamicHtml.view(HtmlTemplate::doctorsTemplate);

    public static void createDoctorsView() throws IOException {
        Stream<Doctor> doctors = Storage.getInstance().getDoctors()
                .stream();

        Path path = Paths.get("doctors.html");
        Files.write(path, template.render(doctors).getBytes());
    }

}

