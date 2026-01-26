package com.hospital.prescription.dto;

import jakarta.validation.constraints.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionItemDTO {

	    @NotNull(message = "Prescription ID is required")
	    private Long prescriptionId;

	    @NotBlank(message = "Medicine name is required")
	    @Size(max = 100, message = "Medicine name must not exceed 100 characters")
	    private String medicineName;

	    @Size(max = 50, message = "Dosage must not exceed 50 characters")
	    private String dosage;

	    @Size(max = 50, message = "Frequency must not exceed 50 characters")
	    private String frequency;

	    @Size(max = 50, message = "Duration must not exceed 50 characters")
	    private String duration;

	    @Size(max = 255, message = "Instructions must not exceed 255 characters")
	    private String instructions;
}