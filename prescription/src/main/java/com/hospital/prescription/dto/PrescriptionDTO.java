package com.hospital.prescription.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionDTO {
	@NotNull(message = "Doctor ID is required")
	private Long doctorId;

	@NotNull(message = "Patient ID is required")
	private Long patientId;

	private Long appointmentId;

	@Size(max = 255, message = "Diagnosis must not exceed 255 characters")
	private String diagnosis;

	@Size(max = 1000, message = "Notes must not exceed 1000 characters")
	private String notes;
	
	private LocalDateTime prescribedDate;
}