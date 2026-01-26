package com.hospital.prescription.service;

import com.hospital.prescription.dto.PrescriptionDTO;
import com.hospital.prescription.dto.PrescriptionItemDTO;
import com.hospital.prescription.entity.Prescription;
import com.hospital.prescription.entity.PrescriptionItem;
import com.hospital.prescription.exception.PrescriptionNotFoundException;
import com.hospital.prescription.repository.PrescriptionItemRepository;
import com.hospital.prescription.repository.PrescriptionRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PrescriptionServiceImpl implements PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final PrescriptionItemRepository prescriptionItemRepository;

    @Override
    public Prescription createPrescription(PrescriptionDTO dto) {

        Prescription prescription = new Prescription();
        prescription.setDoctorId(dto.getDoctorId());
        prescription.setPatientId(dto.getPatientId());
        prescription.setAppointmentId(dto.getAppointmentId());
        prescription.setDiagnosis(dto.getDiagnosis());
        prescription.setNotes(dto.getNotes());
        prescription.setPrescribedDate(dto.getPrescribedDate());
        prescription.setStatus("ACTIVE");

        return prescriptionRepository.save(prescription);
    }

    @Override
    public Prescription getPrescriptionById(Long prescriptionId) {

        return prescriptionRepository.findById(prescriptionId)
                .orElseThrow(() ->
                        new PrescriptionNotFoundException(prescriptionId));
    }

    @Override
    public List<Prescription> getPrescriptionsByDoctor(Long doctorId) {
        return prescriptionRepository.findByDoctorId(doctorId);
    }

    @Override
    public List<Prescription> getPrescriptionsByPatient(Long patientId) {
        return prescriptionRepository.findByPatientId(patientId);
    }

    @Override
    public void deletePrescription(Long prescriptionId) {

        if (!prescriptionRepository.existsById(prescriptionId)) {
            throw new PrescriptionNotFoundException(prescriptionId);
        }

        prescriptionItemRepository.deleteByPrescriptionId(prescriptionId);
        prescriptionRepository.deleteById(prescriptionId);
    }
}
