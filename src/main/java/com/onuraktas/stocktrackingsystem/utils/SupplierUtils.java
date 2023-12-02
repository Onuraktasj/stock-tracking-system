package com.onuraktas.stocktrackingsystem.utils;

import java.util.Objects;
import java.util.UUID;

public class SupplierUtils {

    public static Boolean validateSupplierRequest(UUID supplierId, UUID supplierDtoSupplierID) {
        return !Objects.isNull(supplierId) && !Objects.isNull(supplierDtoSupplierID) && Objects.equals(supplierId, supplierDtoSupplierID);
    }
}
