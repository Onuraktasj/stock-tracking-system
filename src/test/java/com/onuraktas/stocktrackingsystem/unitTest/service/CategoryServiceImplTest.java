package com.onuraktas.stocktrackingsystem.unitTest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import com.onuraktas.stocktrackingsystem.dto.entity.CategoryDto;
import com.onuraktas.stocktrackingsystem.dto.request.CreateCategoryRequest;
import com.onuraktas.stocktrackingsystem.entity.Category;
import com.onuraktas.stocktrackingsystem.exception.CategoryAlreadyExistsException;
import com.onuraktas.stocktrackingsystem.impl.CategoryServiceImpl;
import com.onuraktas.stocktrackingsystem.message.CategoryMessages;
import com.onuraktas.stocktrackingsystem.repository.CategoryRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @InjectMocks
    private CategoryServiceImpl categoryService;
    @Mock
    private CategoryRepository categoryRepository;

    @Test
    void testGetAllCategory_whenCall_thenReturnCategories() {
        // given
        doReturn(List.of(createMockCategory()))
                .when(categoryRepository).findAll();

        // when
        List<CategoryDto> allCategories = categoryService.getAllCategory();

        // then
        assertNotNull(allCategories);
        assertEquals("A", allCategories.get(0).getCategoryName());
    }

    @Test
    void testGetCategory_whenCallWithId_thenReturnCategories() {
        // given
        doReturn(Optional.of(createMockCategory()))
                .when(categoryRepository).findByCategoryIdAndIsActive(any(), any());

        // when
        CategoryDto categoryDto = categoryService.getCategory(UUID.randomUUID());

        // then
        assertNotNull(categoryDto);
        assertEquals("A", categoryDto.getCategoryName());
        assertTrue(categoryDto.getIsActive());
    }


    @Test
    void testCreateCategory_whenCallWithId_thenReturnCategories() {
        // given
        doReturn(createMockCategory())
                .when(categoryRepository).findByCategoryName(any());

        // when
        CategoryAlreadyExistsException exception = assertThrows(CategoryAlreadyExistsException.class,
                () -> categoryService.createCategory(createMockCategoryRequest()));

        // then
        assertNotNull(exception);
        assertEquals(CategoryMessages.CATEGORY_ALREADY_EXIST, exception.getMessage());
    }


    private Category createMockCategory() {
        return new Category(UUID.randomUUID(), "A", Boolean.TRUE);
    }

    private CreateCategoryRequest createMockCategoryRequest() {
        return new CreateCategoryRequest()
                .builder().categoryName("A").build());
    }
}

