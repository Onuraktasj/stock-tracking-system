package com.onuraktas.stocktrackingsystem.impl;

import com.onuraktas.stocktrackingsystem.dto.entity.SupplierDto;
import com.onuraktas.stocktrackingsystem.dto.request.CreateSupplierRequest;
import com.onuraktas.stocktrackingsystem.dto.request.UpdateSupplierContactInfoRequest;
import com.onuraktas.stocktrackingsystem.dto.response.CreateSupplierResponse;
import com.onuraktas.stocktrackingsystem.entity.Supplier;
import com.onuraktas.stocktrackingsystem.entity.enums.Status;
import com.onuraktas.stocktrackingsystem.exception.SupplierNotFoundException;
import com.onuraktas.stocktrackingsystem.mapper.SupplierMapper;
import com.onuraktas.stocktrackingsystem.message.SupplierMessages;
import com.onuraktas.stocktrackingsystem.repository.SupplierRepository;
import com.onuraktas.stocktrackingsystem.service.SupplierService;
import com.onuraktas.stocktrackingsystem.utils.SupplierUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository){
        this.supplierRepository = supplierRepository;
    }
    @Override
    public CreateSupplierResponse createSupplier(CreateSupplierRequest createSupplierRequest) {
        Supplier supplier = this.supplierRepository.save(SupplierMapper.toEntity(createSupplierRequest));
        CreateSupplierResponse createSupplierResponse = SupplierMapper.toCreateSupplierResponse(supplier);
        createSupplierResponse.setStatus(Status.OK.getStatus());

        return createSupplierResponse;
    }

    @Override
    public SupplierDto getSupplier(UUID supplierId) {
        return SupplierMapper.toDto(this.supplierRepository.findById(supplierId).orElseThrow(()->new SupplierNotFoundException(SupplierMessages.SUPPLIER_NOT_FOUND)));
    }

    @Override
    public List<SupplierDto> getAllSupplier() {
        List<Supplier> supplierList = this.supplierRepository.findAllByIsActive(Boolean.TRUE);

        if (supplierList.isEmpty())
            throw new SupplierNotFoundException(SupplierMessages.SUPPLIER_NOT_FOUND);

        return SupplierMapper.toDtoList(supplierList);
    }

    @Override
    public ResponseEntity<SupplierDto> updateSupplier(UUID supplierId, SupplierDto supplierDto) {
        if (Boolean.FALSE.equals(SupplierUtils.validateSupplierRequest(supplierId, supplierDto.getSupplierId())))
            return ResponseEntity.badRequest().build();

        Optional<Supplier> existingSupplier = supplierRepository.findById(supplierId);
        if (existingSupplier.isEmpty())
            throw new SupplierNotFoundException(SupplierMessages.SUPPLIER_NOT_FOUND);

        final SupplierDto updatedSupplier = this.save(supplierDto);
        if (Objects.nonNull(updatedSupplier))
            return ResponseEntity.ok(updatedSupplier);

        return ResponseEntity.internalServerError().build();
    }

    @Override
    public SupplierDto updateSupplierContactInfo(UUID supplierId, UpdateSupplierContactInfoRequest request) {
        Supplier supplier = supplierRepository.findById(supplierId).orElseThrow(()-> new SupplierNotFoundException(SupplierMessages.SUPPLIER_NOT_FOUND));
        supplier.setEmail(request.getEmail());
        supplier.setPhone(request.getPhone());
        supplierRepository.save(supplier);
        return SupplierMapper.toDto(supplier);
    }

    private SupplierDto save(SupplierDto supplierDto){
        Supplier supplier = SupplierMapper.toEntity(supplierDto);
        supplier = supplierRepository.save(supplier);
        return SupplierMapper.toDto(supplier);
    }
}
