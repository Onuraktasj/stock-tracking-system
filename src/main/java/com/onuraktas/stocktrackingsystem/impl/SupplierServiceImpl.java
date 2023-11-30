package com.onuraktas.stocktrackingsystem.impl;

import com.onuraktas.stocktrackingsystem.dto.entity.SupplierDto;
import com.onuraktas.stocktrackingsystem.dto.request.CreateSupplierRequest;
import com.onuraktas.stocktrackingsystem.dto.request.UpdateSupplierContactInfoRequest;
import com.onuraktas.stocktrackingsystem.dto.response.CreateSupplierResponse;
import com.onuraktas.stocktrackingsystem.entity.Supplier;
import com.onuraktas.stocktrackingsystem.entity.enums.Status;
import com.onuraktas.stocktrackingsystem.mapper.SupplierMapper;
import com.onuraktas.stocktrackingsystem.repository.SupplierRepository;
import com.onuraktas.stocktrackingsystem.service.SupplierService;
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
        return SupplierMapper.toDto(this.supplierRepository.findById(supplierId).orElseThrow(()->new NoSuchElementException("Supplier Not Found")));
    }

    @Override
    public List<SupplierDto> getAllSupplier() {
        return SupplierMapper.toDtoList(this.supplierRepository.findAll());
    }

    @Override
    public ResponseEntity<SupplierDto> updateSupplier(UUID supplierId, SupplierDto supplierDto) {
        if (Objects.isNull(supplierId) || Objects.isNull(supplierDto.getSupplierId()) || !Objects.equals(supplierId,supplierDto.getSupplierId()))
            return ResponseEntity.badRequest().build();

        Optional<Supplier> existSupplier = supplierRepository.findById(supplierId);
        if (existSupplier.isEmpty())
            return ResponseEntity.notFound().build();

        final SupplierDto updateSupplier = this.save(supplierDto);
        if (Objects.nonNull(updateSupplier))
            return ResponseEntity.ok(updateSupplier);

        return ResponseEntity.internalServerError().build();
    }

    @Override
    public SupplierDto updateSupplierContactInfo(UUID supplierId, UpdateSupplierContactInfoRequest request) {
        Supplier supplier = supplierRepository.findById(supplierId).orElseThrow(()-> new NoSuchElementException("Supplier Not Found"));
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
