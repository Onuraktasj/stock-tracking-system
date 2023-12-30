package com.onuraktas.stocktrackingsystem.service;

import java.util.List;
import java.util.UUID;

public interface CategoryProductRelService {

    List<UUID> deleteCategoryProductRelByCategoryId(UUID categoryId);
}
