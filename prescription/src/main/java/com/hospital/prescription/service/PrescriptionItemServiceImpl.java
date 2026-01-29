package com.hospital.prescription.service;

import com.hospital.prescription.dto.PrescriptionItemDTO;
import com.hospital.prescription.entity.PrescriptionItem;
import com.hospital.prescription.exception.PrescriptionItemNotFoundException;
import com.hospital.prescription.repository.PrescriptionItemRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PrescriptionItemServiceImpl implements PrescriptionItemService {
    @Autowired
    private PrescriptionItemRepository prescriptionItemRepository;

    @Override
    public PrescriptionItem addItem(PrescriptionItemDTO dto) {

        PrescriptionItem item = new PrescriptionItem();
        item.setPrescriptionId(dto.getPrescriptionId());
        item.setMedicineName(dto.getMedicineName());
        item.setDosage(dto.getDosage());
        item.setFrequency(dto.getFrequency());
        item.setQuantity(dto.getQuantity());
        item.setDuration(dto.getDuration());
        item.setInstructions(dto.getInstructions());
        return prescriptionItemRepository.save(item);
    }

    @Override
    public List<PrescriptionItem> getItemsByPrescription(Long prescriptionId) {
        return prescriptionItemRepository.findByPrescriptionId(prescriptionId);
    }

    @Override
    public PrescriptionItem updateItem(Long itemId, PrescriptionItemDTO dto) {

        PrescriptionItem item = prescriptionItemRepository.findById(itemId)
                .orElseThrow(() ->
                        new PrescriptionItemNotFoundException(itemId));

        item.setMedicineName(dto.getMedicineName());
        item.setDosage(dto.getDosage());
        item.setFrequency(dto.getFrequency());
        item.setQuantity(dto.getQuantity());
        item.setDuration(dto.getDuration());
        item.setInstructions(dto.getInstructions());

        return prescriptionItemRepository.save(item);
    }

    @Override
    public void deleteItem(Long itemId) {

        if (!prescriptionItemRepository.existsById(itemId)) {
            throw new PrescriptionItemNotFoundException(itemId);
        }

        prescriptionItemRepository.deleteById(itemId);
    }

    @Override
    public void deleteItemsByPrescription(Long prescriptionId) {
        prescriptionItemRepository.deleteByPrescriptionId(prescriptionId);
    }
}
