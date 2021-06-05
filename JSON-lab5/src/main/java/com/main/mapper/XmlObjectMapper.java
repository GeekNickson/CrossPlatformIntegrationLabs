package com.main.mapper;

import com.main.model.common.Doctor;
import com.main.model.common.Patient;
import com.main.model.common.Service;
import com.main.model.common.Specialty;
import com.main.model.xml.DoctorXml;
import com.main.model.xml.PatientXml;
import com.main.model.xml.ServiceXml;
import com.main.model.xml.SpecialtyXml;
import org.springframework.stereotype.Component;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class XmlObjectMapper {

    public Doctor toDoctor(DoctorXml doctorXml) {
        Doctor doctor = new Doctor();
        doctor.setId(doctorXml.getId());
        doctor.setFirstName(doctorXml.getFirstName());
        doctor.setLastName(doctorXml.getLastName());
        doctor.setAge(doctorXml.getAge());
        doctor.setCategory(doctorXml.getCategory().value());
        doctor.setExperience((short) doctorXml.getExperience());

        XMLGregorianCalendar vacationStartXml = doctorXml.getVacationStart();
        LocalDate vacationStart = LocalDate
                .of(vacationStartXml.getYear(), vacationStartXml.getMonth(), vacationStartXml.getDay());
        doctor.setVacationStart(vacationStart);

        XMLGregorianCalendar vacationEndXml = doctorXml.getVacationEnd();
        LocalDate vacationEnd = LocalDate
                .of(vacationEndXml.getYear(), vacationEndXml.getMonth(), vacationEndXml.getDay());
        doctor.setVacationEnd(vacationEnd);

        if (doctorXml.getSpecialtyRef() != null) {
            doctor.setSpecialty(toSpecialty((SpecialtyXml) doctorXml.getSpecialtyRef()));
        }

        if (doctorXml.getServicesRef() != null && doctorXml.getServicesRef().size() > 0) {
            List<Service> services = new ArrayList<>();
            doctorXml.getServicesRef().forEach(serviceRef -> services.add(toService((ServiceXml) serviceRef)));
            doctor.setServices(services);
        }

        return doctor;
    }

    public Specialty toSpecialty(SpecialtyXml specialtyXml) {
        Specialty specialty = new Specialty();
        specialty.setId(specialtyXml.getId());
        specialty.setName(specialtyXml.getName());
        return specialty;
    }

    public Service toService(ServiceXml serviceXml) {
        Service service = new Service();
        service.setId(serviceXml.getId());
        service.setName(serviceXml.getName());
        service.setPrice(serviceXml.getPrice().doubleValue());
        return service;
    }

    public Patient toPatient(PatientXml patientXml) {
        Patient patient = new Patient();
        patient.setId(patientXml.getId());
        patient.setFirstName(patient.getFirstName());
        patient.setLastName(patientXml.getLastName());
        patient.setAge(patientXml.getAge());
        patient.setInsured(patientXml.isInsured());
        return patient;
    }
}
