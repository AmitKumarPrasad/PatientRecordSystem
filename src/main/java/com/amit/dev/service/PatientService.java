package com.amit.dev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amit.dev.model.Patient;
import com.amit.dev.repository.PatientRepository;

@Service
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;

	public List<Patient> getAllPatientDetails() {
		return patientRepository.findAll();
	}

	public Patient newPatient(Patient patient) {
		return patientRepository.save(patient);
	}

	public Patient findByPatient(int pId) {
		return patientRepository.getOne(pId);
	}

	public Patient updatePatient(int updateId) {
		return patientRepository.getOne(updateId);
	}

	public void deletePatient(int deletePId) {
		patientRepository.deleteById(deletePId);
	}

}
