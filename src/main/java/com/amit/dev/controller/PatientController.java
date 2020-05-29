package com.amit.dev.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amit.dev.exception.UserNotFoundException;
import com.amit.dev.model.Patient;
import com.amit.dev.service.PatientService;

@RestController
@RequestMapping("/v2/patient/api")
public class PatientController {

	@Autowired
	private PatientService service;

	@GetMapping("/All/Patient")
	public List<Patient> getAllDetails() {
		return service.getAllPatientDetails();
	}

	@PostMapping("/savePatient")
	public ResponseEntity<String> newPatientRegister(@Valid @RequestBody Patient patient) {
		service.newPatient(patient);
		return new ResponseEntity<String>("Successfull Register", HttpStatus.CREATED);
	}

	@GetMapping("/findId/{id}")
	public ResponseEntity<Patient> getByPatientId(@PathVariable("id") Integer pId) {
		Patient patient = service.findByPatient(pId);
		if (patient == null) {
			return new ResponseEntity<Patient>(patient, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Patient>(patient, HttpStatus.ACCEPTED);
		}

	}

	@PutMapping("/patient/{id}")
	public ResponseEntity<Patient> updateByPatient(@PathVariable("id") Integer pId,
			@Valid @RequestBody Patient patient) {
		Patient update = service.updatePatient(pId);
		if (update == null) {
			return new ResponseEntity<Patient>(update, HttpStatus.BAD_REQUEST);
		}
		update.setName(patient.getName());
		update.setAge(patient.getAge());
		update.setBloodPressure(patient.getBloodPressure());
		update.setHeight(patient.getHeight());
		update.setWeight(patient.getWeight());

		Patient thePatient = service.newPatient(update);
		return new ResponseEntity<Patient>(thePatient, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletePatient(@PathVariable("id") Integer id) {
		service.deletePatient(id);
		return new ResponseEntity<String>("Delete The Patient Recorde", HttpStatus.OK);
	}

}
