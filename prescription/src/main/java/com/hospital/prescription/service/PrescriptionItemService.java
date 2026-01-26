package com.hospital.prescription.service;

import com.hospital.prescription.dto.PrescriptionItemDTO;
import com.hospital.prescription.entity.PrescriptionItem;

import java.util.List;

public interface PrescriptionItemService {

    PrescriptionItem addItem(PrescriptionItemDTO dto);

    List<PrescriptionItem> getItemsByPrescription(Long prescriptionId);

    PrescriptionItem updateItem(Long itemId, PrescriptionItemDTO dto);

    void deleteItem(Long itemId);

    void deleteItemsByPrescription(Long prescriptionId);
}

