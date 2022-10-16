package com.intcomex.store.services;

import com.intcomex.store.models.entity.Category;
import com.intcomex.store.repositories.CategoryRepository;
import com.intcomex.store.repositories.services.CategoryServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceImplTest {

    @Mock
    private CategoryRepository mockRepository;

    @InjectMocks
    private CategoryServiceImpl categoryServiceImplUnderTest;

    @Test
    public void testGetAll() {
        // Setup
        // Configure CategoryRepository.findAll(...).
        final Category category = new Category();
        category.setCategoryId(0L);
        category.setCategoryName("categoryName");
        category.setDescription("description");
        category.setPicture("picture");
        final Iterable<Category> categories = Arrays.asList(category);
        when(mockRepository.findAll()).thenReturn(categories);

        // Run the test
        final List<Category> result = categoryServiceImplUnderTest.getAll();

        // Verify the results
    }

    @Test
    public void testGetAll_CategoryRepositoryReturnsNoItems() {
        // Setup
        when(mockRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Category> result = categoryServiceImplUnderTest.getAll();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    public void testGetById() {
        // Setup
        // Configure CategoryRepository.findById(...).
        final Category category1 = new Category();
        category1.setCategoryId(0L);
        category1.setCategoryName("categoryName");
        category1.setDescription("description");
        category1.setPicture("picture");
        final Optional<Category> category = Optional.of(category1);
        when(mockRepository.findById(0L)).thenReturn(category);

        // Run the test
        final Optional<Category> result = categoryServiceImplUnderTest.getById(0L);

        // Verify the results
    }

    @Test
    public void testGetById_CategoryRepositoryReturnsAbsent() {
        // Setup
        when(mockRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final Optional<Category> result = categoryServiceImplUnderTest.getById(0L);

        // Verify the results
        assertThat(result).isEmpty();
    }

    @Test
    public void testSave() {
        // Setup
        final Category category = new Category();
        category.setCategoryId(0L);
        category.setCategoryName("categoryName");
        category.setDescription("description");
        category.setPicture("picture");

        // Configure CategoryRepository.save(...).
        final Category category1 = new Category();
        category1.setCategoryId(0L);
        category1.setCategoryName("categoryName");
        category1.setDescription("description");
        category1.setPicture("picture");
        when(mockRepository.save(any(Category.class))).thenReturn(category1);

        // Run the test
        final Category result = categoryServiceImplUnderTest.save(category);

        // Verify the results
    }

    @Test
    public void testDelete() {
        // Setup
        // Run the test
        categoryServiceImplUnderTest.delete(0L);

        // Verify the results
        verify(mockRepository).deleteById(0L);
    }
}
