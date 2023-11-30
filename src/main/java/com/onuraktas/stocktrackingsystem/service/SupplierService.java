package com.onuraktas.stocktrackingsystem.service;

import com.onuraktas.stocktrackingsystem.dto.entity.SupplierDto;
import com.onuraktas.stocktrackingsystem.dto.request.CreateSupplierRequest;
import com.onuraktas.stocktrackingsystem.dto.request.UpdateSupplierContactInfoRequest;
import com.onuraktas.stocktrackingsystem.dto.response.CreateSupplierResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface SupplierService {

    CreateSupplierResponse createSupplier(CreateSupplierRequest createSupplierRequest);

    SupplierDto getSupplier(UUID supplierId);

    List<SupplierDto> getAllSupplier();

    ResponseEntity<SupplierDto> updateSupplier(UUID supplierId, SupplierDto supplierDto);

    SupplierDto updateSupplierContactInfo(UUID supplierId,UpdateSupplierContactInfoRequest request);



}
