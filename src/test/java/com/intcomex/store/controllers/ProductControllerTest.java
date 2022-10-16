package com.intcomex.store.controllers;

import com.intcomex.store.models.entity.Category;
import com.intcomex.store.models.entity.Product;
import com.intcomex.store.models.entity.Supplier;
import com.intcomex.store.services.CategoryService;
import com.intcomex.store.services.ProductService;
import com.intcomex.store.services.SupplierService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
public class ProductControllerTest {

    @Mock
    private ProductService mockService;
    @Mock
    private SupplierService mockSupplierService;
    @Mock
    private CategoryService mockCategoryService;

    @InjectMocks
    private ProductController productControllerUnderTest;

    @Test
    public void testGetAllProducts() {
        // Setup
        // Configure ProductService.getAll(...).
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
        final List<Product> products = Arrays.asList(product);
        when(mockService.getAll(0, 0)).thenReturn(products);

        // Run the test
        final ResponseEntity<List<Product>> result = productControllerUnderTest.getAllProducts(0, 0);

        // Verify the results
    }

    @Test
    public void testGetAllProducts_ProductServiceReturnsNoItems() {
        // Setup
        when(mockService.getAll(0, 0)).thenReturn(Collections.emptyList());

        // Run the test
        final ResponseEntity<List<Product>> result = productControllerUnderTest.getAllProducts(0, 0);

        // Verify the results
        assertThat(result).isEqualTo(ResponseEntity.ok(Collections.emptyList()));
    }

    @Test
    public void testGetSingleProduct() {
        // Setup
        final ResponseEntity<?> expectedResult = new ResponseEntity<>(null, HttpStatus.OK);

        // Configure ProductService.getProductById(...).
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
        when(mockService.getProductById(0L)).thenReturn(product);

        // Configure CategoryService.getById(...).
        final Category category1 = new Category();
        category1.setCategoryId(0L);
        category1.setCategoryName("categoryName");
        category1.setDescription("description");
        category1.setPicture("categoryPicture");
        final Optional<Category> category = Optional.of(category1);
        when(mockCategoryService.getById(0L)).thenReturn(category);

        // Run the test
        final ResponseEntity<?> result = productControllerUnderTest.getSingleProduct(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testGetSingleProduct_ProductServiceReturnsAbsent() {
        // Setup
        final ResponseEntity<?> expectedResult = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        when(mockService.getProductById(0L)).thenReturn(Optional.empty());

        // Run the test
        final ResponseEntity<?> result = productControllerUnderTest.getSingleProduct(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testGetSingleProduct_CategoryServiceReturnsAbsent() {
        // Setup
        final ResponseEntity<?> expectedResult = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        // Configure ProductService.getProductById(...).
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
        when(mockService.getProductById(0L)).thenReturn(product);

        when(mockCategoryService.getById(0L)).thenReturn(Optional.empty());

        // Run the test
        final ResponseEntity<?> result = productControllerUnderTest.getSingleProduct(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testCreateProduct() {
        // Setup
        final Product product = new Product();
        product.setCategoryPicture("categoryPicture");
        product.setProductName("productName");
        product.setQuantityPerUnit(0);
        product.setUnitPrice(0.0);
        product.setUnitsInStock(0);
        product.setUnitsInOrder(0);
        product.setReorderLevel("reorderLevel");
        product.setDiscontinued(false);
        product.setSupplierId(0L);
        product.setCategoryId(0L);

        final BindingResult bindingResult = Mockito.any(BindingResult.class);
        final ResponseEntity<?> expectedResult = new ResponseEntity<>(null, HttpStatus.OK);

        // Configure SupplierService.getSupplierById(...).
        final Supplier supplier1 = new Supplier();
        supplier1.setSupplierId(1L);
        supplier1.setCompanyName("companyName");
        supplier1.setContactName("contactName");
        supplier1.setContactTitle("contactTitle");
        supplier1.setAddress("address");
        supplier1.setCity("city");
        supplier1.setRegion("region");
        supplier1.setPostalCode("postalCode");
        supplier1.setCountry("country");
        supplier1.setPhone("phone");
        supplier1.setFax(0);
        supplier1.setHomePage("homePage");
        final Optional<Supplier> supplier = Optional.of(supplier1);
        when(mockSupplierService.getSupplierById(1L)).thenReturn(supplier);

        // Configure CategoryService.getById(...).
        final Category category1 = new Category();
        category1.setCategoryId(1L);
        category1.setCategoryName("categoryName");
        category1.setDescription("description");
        category1.setPicture("categoryPicture");
        final Optional<Category> category = Optional.of(category1);
        when(mockCategoryService.getById(1L)).thenReturn(category);

        // Configure ProductService.save(...).
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
        when(mockService.save(any(Product.class))).thenReturn(product1);

        // Run the test
        final ResponseEntity<?> result = productControllerUnderTest.createProduct(product, bindingResult);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testCreateProduct_SupplierServiceReturnsAbsent() {
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

        final BindingResult bindingResult = null;
        final ResponseEntity<?> expectedResult = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        when(mockSupplierService.getSupplierById(0L)).thenReturn(Optional.empty());

        // Configure CategoryService.getById(...).
        final Category category1 = new Category();
        category1.setCategoryId(0L);
        category1.setCategoryName("categoryName");
        category1.setDescription("description");
        category1.setPicture("categoryPicture");
        final Optional<Category> category = Optional.of(category1);
        when(mockCategoryService.getById(0L)).thenReturn(category);

        // Run the test
        final ResponseEntity<?> result = productControllerUnderTest.createProduct(product, bindingResult);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testCreateProduct_CategoryServiceReturnsAbsent() {
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

        final BindingResult bindingResult = null;
        final ResponseEntity<?> expectedResult = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        // Configure SupplierService.getSupplierById(...).
        final Supplier supplier1 = new Supplier();
        supplier1.setSupplierId(0L);
        supplier1.setCompanyName("companyName");
        supplier1.setContactName("contactName");
        supplier1.setContactTitle("contactTitle");
        supplier1.setAddress("address");
        supplier1.setCity("city");
        supplier1.setRegion("region");
        supplier1.setPostalCode("postalCode");
        supplier1.setCountry("country");
        supplier1.setPhone("phone");
        supplier1.setFax(0);
        supplier1.setHomePage("homePage");
        final Optional<Supplier> supplier = Optional.of(supplier1);
        when(mockSupplierService.getSupplierById(0L)).thenReturn(supplier);

        when(mockCategoryService.getById(0L)).thenReturn(Optional.empty());

        // Run the test
        final ResponseEntity<?> result = productControllerUnderTest.createProduct(product, bindingResult);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testDeleteProduct() {
        // Setup
        final ResponseEntity<?> expectedResult = new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

        // Configure ProductService.getProductById(...).
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
        when(mockService.getProductById(0L)).thenReturn(product);

        // Run the test
        final ResponseEntity<?> result = productControllerUnderTest.deleteProduct(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockService).delete(0L);
    }

    @Test
    public void testDeleteProduct_ProductServiceGetProductByIdReturnsAbsent() {
        // Setup
        final ResponseEntity<?> expectedResult = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        when(mockService.getProductById(0L)).thenReturn(Optional.empty());

        // Run the test
        final ResponseEntity<?> result = productControllerUnderTest.deleteProduct(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
