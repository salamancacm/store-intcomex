package com.intcomex.store.controllers;

import com.intcomex.store.models.entity.Category;
import com.intcomex.store.repositories.services.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategoryControllerTest {

    @Mock
    private CategoryService mockService;

    @InjectMocks
    private CategoryController categoryControllerUnderTest;

    @Test
    public void testGetCategories() {
        // Setup
        // Configure CategoryService.getAll(...).
        final Category category = new Category();
        category.setCategoryId(0L);
        category.setCategoryName("categoryName");
        category.setDescription("description");
        category.setPicture("picture");
        final List<Category> categories = Arrays.asList(category);
        when(mockService.getAll()).thenReturn(categories);

        // Run the test
        final ResponseEntity<List<Category>> result = categoryControllerUnderTest.getCategories();

        // Verify the results
    }

    @Test
    public void testGetCategories_CategoryServiceReturnsNoItems() {
        // Setup
        when(mockService.getAll()).thenReturn(Collections.emptyList());

        // Run the test
        final ResponseEntity<List<Category>> result = categoryControllerUnderTest.getCategories();

        // Verify the results
        assertThat(result).isEqualTo(ResponseEntity.ok(Collections.emptyList()));
    }

    @Test
    public void testCreateCategory() {
        // Setup
        final Category category = new Category();
        category.setCategoryId(0L);
        category.setCategoryName("categoryName");
        category.setDescription("description");
        category.setPicture("picture");

        final BindingResult bindingResult = null;
        final ResponseEntity<?> expectedResult = new ResponseEntity<>(null, HttpStatus.OK);

        // Configure CategoryService.save(...).
        final Category category1 = new Category();
        category1.setCategoryId(0L);
        category1.setCategoryName("categoryName");
        category1.setDescription("description");
        category1.setPicture("picture");
        when(mockService.save(any(Category.class))).thenReturn(category1);

        // Run the test
        final ResponseEntity<?> result = categoryControllerUnderTest.createCategory(category, bindingResult);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testDeleteCategory() {
        // Setup
        final ResponseEntity<?> expectedResult = new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

        // Configure CategoryService.getById(...).
        final Category category1 = new Category();
        category1.setCategoryId(0L);
        category1.setCategoryName("categoryName");
        category1.setDescription("description");
        category1.setPicture("picture");
        final Optional<Category> category = Optional.of(category1);
        when(mockService.getById(0L)).thenReturn(category);

        // Run the test
        final ResponseEntity<?> result = categoryControllerUnderTest.deleteCategory(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockService).delete(0L);
    }

    @Test
    public void testDeleteCategory_CategoryServiceGetByIdReturnsAbsent() {
        // Setup
        final ResponseEntity<?> expectedResult = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        when(mockService.getById(0L)).thenReturn(Optional.empty());

        // Run the test
        final ResponseEntity<?> result = categoryControllerUnderTest.deleteCategory(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
