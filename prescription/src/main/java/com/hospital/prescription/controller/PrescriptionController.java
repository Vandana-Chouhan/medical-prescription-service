package com.hospital.prescription.controller;

import com.hospital.prescription.dto.ApiResponseDTO;
import com.hospital.prescription.dto.PrescriptionDTO;
import com.hospital.prescription.dto.PrescriptionItemDTO;
import com.hospital.prescription.dto.PrescriptionMedicineDTO;
import com.hospital.prescription.dto.PrescriptionResponseDTO;
import com.hospital.prescription.entity.Prescription;
import com.hospital.prescription.entity.PrescriptionItem;
import com.hospital.prescription.service.PrescriptionItemService;
import com.hospital.prescription.service.PrescriptionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/prescriptions")

public class PrescriptionController {
    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private PrescriptionItemService prescriptionItemService;
    
    // 1️ Create prescription
    @PostMapping
    public Prescription createPrescription(
            @Valid @RequestBody PrescriptionDTO dto) {

        return prescriptionService.createPrescription(dto);
    }

    // 2 Get prescription by ID
    @GetMapping("/{id}")
    public Prescription getPrescription(@PathVariable Long id) {
        return prescriptionService.getPrescriptionById(id);
    }

    // 3 Get prescriptions by doctor
    @GetMapping("/doctor/{doctorId}")
    public List<Prescription> getByDoctor(@PathVariable Long doctorId) {
        return prescriptionService.getPrescriptionsByDoctor(doctorId);
    }

    // 4 Get prescriptions by patient
    @GetMapping("/patient/{patientId}")
    public List<Prescription> getByPatient(@PathVariable Long patientId) {
        return prescriptionService.getPrescriptionsByPatient(patientId);
    }

    // 5 Delete prescription
    @DeleteMapping("/{id}")
    public ApiResponseDTO deletePrescription(@PathVariable Long id) {
        prescriptionService.deletePrescription(id);
        return new ApiResponseDTO("Prescription deleted successfully");
    }
  //USED BY PHARMACY SERVICE
    @GetMapping("/with-items/{id}")
    public PrescriptionResponseDTO getPrescriptionWithItems(@PathVariable Long id) {

        Prescription prescription = prescriptionService.getPrescriptionById(id);

        List<PrescriptionItem> items =
                prescriptionItemService.getItemsByPrescription(id);

        PrescriptionResponseDTO dto = new PrescriptionResponseDTO();
        dto.setPrescriptionId(prescription.getPrescriptionId());

        List<PrescriptionMedicineDTO> meds = new ArrayList<>();

        for (PrescriptionItem item : items) {
            PrescriptionMedicineDTO m = new PrescriptionMedicineDTO();
            m.setMedicineName(item.getMedicineName());
            m.setQuantity(item.getQuantity()); // ✅ FIXED (not hardcoded)
            meds.add(m);
        }

        dto.setMedicines(meds);
        return dto;
    }
}
