package com.onuraktas.stocktrackingsystem.mapper;

import com.onuraktas.stocktrackingsystem.dto.entity.SupplierDto;
import com.onuraktas.stocktrackingsystem.dto.request.CreateSupplierRequest;
import com.onuraktas.stocktrackingsystem.dto.response.CreateSupplierResponse;
import com.onuraktas.stocktrackingsystem.entity.Supplier;

import java.util.List;
import java.util.Objects;

public class SupplierMapper {

    public static SupplierDto toDto(Supplier supplier){
        if (Objects.isNull(supplier))
            return null;
        return SupplierDto.builder()
                .supplierId(supplier.getSupplierId())
                .supplierName(supplier.getSupplierName())
                .phone(supplier.getPhone())
                .email(supplier.getEmail())
                .isActive(supplier.getIsActive())
                .build();
    }

    public static Supplier toEntity(SupplierDto supplierDto){
        if (Objects.isNull(supplierDto))
            return null;

        return Supplier.builder()
                .supplierId(supplierDto.getSupplierId())
                .supplierName(supplierDto.getSupplierName())
                .phone(supplierDto.getPhone())
                .email(supplierDto.getEmail())
                .isActive(supplierDto.getIsActive())
                .build();
    }

    public static Supplier toEntity(CreateSupplierRequest createSupplierRequest){
        if (Objects.isNull(createSupplierRequest))
            return null;

        return Supplier.builder()
                .supplierName(createSupplierRequest.getSupplierName())
                .phone(createSupplierRequest.getPhone())
                .email(createSupplierRequest.getEmail())
                .build();
    }

    public static CreateSupplierResponse toCreateSupplierResponse(Supplier supplier){
        if (Objects.isNull(supplier))
            return null;
        return CreateSupplierResponse.builder()
                .supplierId(supplier.getSupplierId())
                .supplierName(supplier.getSupplierName())
                .phone(supplier.getPhone())
                .email(supplier.getEmail())
                .isActive(supplier.getIsActive())
                .build();

    }

    public static List<SupplierDto> toDtoList(List<Supplier> suppliers){
        return suppliers.stream().parallel()
                .map(SupplierMapper::toDto)
                .toList();
    }
}
