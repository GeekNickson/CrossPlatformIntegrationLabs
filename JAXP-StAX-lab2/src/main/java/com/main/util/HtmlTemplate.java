package com.main.util;

import com.main.model.Doctor;
import com.main.model.Service;
import htmlflow.DynamicHtml;
import org.xmlet.htmlapifaster.EnumRelType;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class HtmlTemplate {

    public static void doctorsTemplate(DynamicHtml<Stream<Doctor>> view, Stream<Doctor> doctors) {
        AtomicInteger workingDoctors = new AtomicInteger();

        view.html()
                .head()
                    .meta()
                        .attrCharset("UTF-8")
                    .__()
                    .link()
                        .attrRel(EnumRelType.STYLESHEET)
                        .attrHref("https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css")
                    .__()
                    .title()
                        .text("Tasks Table")
                    .__()
                .__()
                .body()
                    .h1().attrClass("center-align").text("Doctors").__()
                    .div()
                    .attrClass("container")
                        .table()
                        .attrClass("responsive-table highlight")
                            .thead()
                                .tr()
                                    .th().text("First Name").__()
                                    .th().text("Last Name").__()
                                    .th().text("Category").__()
                                    .th().text("Specialty").__()
                                    .th().text("Services").__()
                                    .th().text("Prices").__()
                                    .th().text("Vacation Start").__()
                                    .th().text("Vacation End").__()
                                    .th().text("On Vacation").__()
                                .__()
                            .__()
                            .tbody()
                                .dynamic(tableBody ->
                                        doctors.forEach(doctor -> {
                                            if (!doctor.onVacation()) {
                                                workingDoctors.getAndIncrement();
                                            }

                                            tableBody
                                                    .tr()
                                                        .td().text(doctor.getFirstName()).__()
                                                        .td().text(doctor.getLastName()).__()
                                                        .td().text(doctor.getCategory()).__()
                                                        .td().text(doctor.getSpecialty().getName()).__()
                                                        .td().of(td -> doctor.getServices()
                                                                .forEach(service -> td
                                                                        .p().text(service.getName()).__()))
                                                        .__()
                                                        .td().of(td -> doctor.getServices()
                                                        .forEach(service -> td
                                                                .p().text(service.getPrice()).__()))
                                                        .__()
                                                        .td().text(doctor.getVacationStart()).__()
                                                        .td().text(doctor.getVacationEnd()).__()
                                                        .td().text(doctor.onVacation() ? "Yes" : "No")
                                                    .__();
                                        })
                                    )
                                .__()
                                .tfoot()
                                    .tr()
                                        .th().text("Doctors working").__()
                                        .td().text(workingDoctors).__()
                                    .__()
                                .__()
                            .__()
                        .__()
                    .__()
                .__();
    }
}
