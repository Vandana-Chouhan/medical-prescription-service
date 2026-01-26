package com.hospital.prescription.controller;

import com.hospital.prescription.dto.ApiResponseDTO;
import com.hospital.prescription.dto.PrescriptionItemDTO;
import com.hospital.prescription.entity.PrescriptionItem;
import com.hospital.prescription.service.PrescriptionItemService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prescription-items")
@RequiredArgsConstructor
public class PrescriptionItemController {

	private final PrescriptionItemService prescriptionItemService;

	// 1️ Add medicine
	@PostMapping
	public PrescriptionItem addItem(@Valid @RequestBody PrescriptionItemDTO dto) {

		return prescriptionItemService.addItem(dto);
	}

	// 2️ Get medicines by prescription
	@GetMapping("/prescription/{prescriptionId}")
	public List<PrescriptionItem> getItems(@PathVariable Long prescriptionId) {

		return prescriptionItemService.getItemsByPrescription(prescriptionId);
	}

	// 3️ Update medicine
	@PutMapping("/{itemId}")
	public ResponseEntity<ApiResponseDTO> updateItem(@PathVariable Long itemId,
			@Valid @RequestBody PrescriptionItemDTO dto) {

		prescriptionItemService.updateItem(itemId, dto);

		return ResponseEntity.ok(new ApiResponseDTO("Prescription item updated successfully"));
	}

    // 4️ Delete one medicine
	@DeleteMapping("/{itemId}")
	public ResponseEntity<ApiResponseDTO> deleteItem(@PathVariable Long itemId) {

		prescriptionItemService.deleteItem(itemId);

		return ResponseEntity.ok(new ApiResponseDTO("Prescription item deleted successfully"));
	}
}
