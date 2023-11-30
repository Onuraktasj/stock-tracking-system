package com.onuraktas.stocktrackingsystem.controller;

import com.onuraktas.stocktrackingsystem.dto.entity.SupplierDto;
import com.onuraktas.stocktrackingsystem.dto.request.CreateSupplierRequest;
import com.onuraktas.stocktrackingsystem.dto.request.UpdateSupplierContactInfoRequest;
import com.onuraktas.stocktrackingsystem.dto.response.CreateSupplierResponse;
import com.onuraktas.stocktrackingsystem.service.SupplierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/supplier")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService){
        this.supplierService = supplierService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<CreateSupplierResponse> createSupplier(@RequestBody CreateSupplierRequest createSupplierRequest){
        return ResponseEntity.ok(supplierService.createSupplier(createSupplierRequest));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<SupplierDto>> getAllSupplier(){
        return ResponseEntity.ok(supplierService.getAllSupplier());
    }

    @GetMapping("/{supplierId}")
    public ResponseEntity<SupplierDto> getSupplier(@PathVariable(value = "supplierId") UUID supplierId){
        return ResponseEntity.ok(supplierService.getSupplier(supplierId));
    }

    @PutMapping("/{supplierId}")
    public ResponseEntity<SupplierDto> updateSupplier(@PathVariable(value = "supplierId")UUID supplierId, @RequestBody SupplierDto supplierDto){
        return supplierService.updateSupplier(supplierId,supplierDto);
    }

    @PatchMapping("/{supplierId}")
    public ResponseEntity<SupplierDto> updateSupplierContactInfo(@PathVariable(value = "supplierId") UUID supplierId, @RequestBody UpdateSupplierContactInfoRequest request){
        return ResponseEntity.ok(supplierService.updateSupplierContactInfo(supplierId,request));
    }
}
