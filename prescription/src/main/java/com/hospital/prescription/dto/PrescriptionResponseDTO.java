package com.hospital.prescription.dto;

import java.util.List;

public class PrescriptionResponseDTO {

	private Long prescriptionId;
	private List<PrescriptionMedicineDTO> medicines;

	public Long getPrescriptionId() {
		return prescriptionId;
	}

	public void setPrescriptionId(Long prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

	public List<PrescriptionMedicineDTO> getMedicines() {
		return medicines;
	}

	public void setMedicines(List<PrescriptionMedicineDTO> medicines) {
		this.medicines = medicines;
	}
}