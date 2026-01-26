package com.hospital.prescription.exception;

public class PrescriptionItemNotFoundException extends RuntimeException {

	public PrescriptionItemNotFoundException(Long id) {
		super("Prescription item not found with id: " + id);
	}
}
