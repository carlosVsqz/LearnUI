package com.starterkit.springboot.brs.controller;

import com.starterkit.springboot.brs.pojo.Patient;
import com.starterkit.springboot.brs.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
public class PatientController {
    final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("example/getPatient")
    public Patient getPatient(@RequestParam String name) throws ExecutionException, InterruptedException {
        return patientService.getPatientDetails(name);
    }

    @PostMapping("example/createPatient")
    public String createPatient(@RequestBody Patient patient) throws ExecutionException, InterruptedException {
        return patientService.savePatientDetails(patient);
    }
    @PutMapping("example/updatePatient")
    public String updatePatient(@RequestBody Patient patient) throws ExecutionException, InterruptedException {
        return patientService.updatePatientDetails(patient);
    }
    @DeleteMapping("example/deletePatient")
    public String deletePatient(@RequestParam String name){
        return patientService.deletePatientDetails(name);
    }
}
