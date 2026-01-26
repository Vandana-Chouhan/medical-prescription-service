package com.hospital.prescription.service;

import com.hospital.prescription.dto.PrescriptionDTO;
import com.hospital.prescription.dto.PrescriptionItemDTO;
import com.hospital.prescription.entity.Prescription;
import com.hospital.prescription.entity.PrescriptionItem;

import java.util.List;

public interface PrescriptionService {

	Prescription createPrescription(PrescriptionDTO dto);

	Prescription getPrescriptionById(Long prescriptionId);

	List<Prescription> getPrescriptionsByDoctor(Long doctorId);

	List<Prescription> getPrescriptionsByPatient(Long patientId);

	void deletePrescription(Long prescriptionId);
}
