package com.intcomex.store.services;

import com.intcomex.store.models.entity.Product;
import com.intcomex.store.repositories.ProductRepository;
import com.intcomex.store.repositories.services.ProductServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository mockRepository;

    @InjectMocks
    private ProductServiceImpl productServiceImplUnderTest;

    @Test
    public void testGetAll() {
        // Setup
        // Configure ProductRepository.findAll(...).
        final Product product = new Product();
        product.setCategoryPicture("categoryPicture");
        product.setProductId(0L);
        product.setProductName("productName");
        product.setQuantityPerUnit(0);
        product.setUnitPrice(0.0);
        product.setUnitsInStock(0);
        product.setUnitsInOrder(0);
        product.setReorderLevel("reorderLevel");
        product.setDiscontinued(false);
        product.setSupplierId(0L);
        product.setCategoryId(0L);
        final Page<Product> products = new PageImpl<>(Arrays.asList(product));
        when(mockRepository.findAll(any(Pageable.class))).thenReturn(products);

        // Run the test
        final List<Product> result = productServiceImplUnderTest.getAll(0, 0);

        // Verify the results
    }

    @Test
    public void testGetAll_ProductRepositoryReturnsNoItems() {
        // Setup
        when(mockRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(Collections.emptyList()));

        // Run the test
        final List<Product> result = productServiceImplUnderTest.getAll(0, 0);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    public void testGetProductById() {
        // Setup
        // Configure ProductRepository.findById(...).
        final Product product1 = new Product();
        product1.setCategoryPicture("categoryPicture");
        product1.setProductId(0L);
        product1.setProductName("productName");
        product1.setQuantityPerUnit(0);
        product1.setUnitPrice(0.0);
        product1.setUnitsInStock(0);
        product1.setUnitsInOrder(0);
        product1.setReorderLevel("reorderLevel");
        product1.setDiscontinued(false);
        product1.setSupplierId(0L);
        product1.setCategoryId(0L);
        final Optional<Product> product = Optional.of(product1);
        when(mockRepository.findById(0L)).thenReturn(product);

        // Run the test
        final Optional<Product> result = productServiceImplUnderTest.getProductById(0L);

        // Verify the results
    }

    @Test
    public void testGetProductById_ProductRepositoryReturnsAbsent() {
        // Setup
        when(mockRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final Optional<Product> result = productServiceImplUnderTest.getProductById(0L);

        // Verify the results
        assertThat(result).isEmpty();
    }

    @Test
    public void testSave() {
        // Setup
        final Product product = new Product();
        product.setCategoryPicture("categoryPicture");
        product.setProductId(0L);
        product.setProductName("productName");
        product.setQuantityPerUnit(0);
        product.setUnitPrice(0.0);
        product.setUnitsInStock(0);
        product.setUnitsInOrder(0);
        product.setReorderLevel("reorderLevel");
        product.setDiscontinued(false);
        product.setSupplierId(0L);
        product.setCategoryId(0L);

        // Configure ProductRepository.save(...).
        final Product product1 = new Product();
        product1.setCategoryPicture("categoryPicture");
        product1.setProductId(0L);
        product1.setProductName("productName");
        product1.setQuantityPerUnit(0);
        product1.setUnitPrice(0.0);
        product1.setUnitsInStock(0);
        product1.setUnitsInOrder(0);
        product1.setReorderLevel("reorderLevel");
        product1.setDiscontinued(false);
        product1.setSupplierId(0L);
        product1.setCategoryId(0L);
        when(mockRepository.save(any(Product.class))).thenReturn(product1);

        // Run the test
        final Product result = productServiceImplUnderTest.save(product);

        // Verify the results
    }

    @Test
    public void testDelete() {
        // Setup
        // Run the test
        productServiceImplUnderTest.delete(0L);

        // Verify the results
        verify(mockRepository).deleteById(0L);
    }
}
