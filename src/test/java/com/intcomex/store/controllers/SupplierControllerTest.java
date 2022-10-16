package com.intcomex.store.controllers;

import com.intcomex.store.models.entity.Supplier;
import com.intcomex.store.repositories.services.SupplierService;
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
public class SupplierControllerTest {

    @Mock
    private SupplierService mockService;

    @InjectMocks
    private SupplierController supplierControllerUnderTest;

    @Test
    public void testGetAllSuppliers() {
        // Setup
        // Configure SupplierService.getAll(...).
        final Supplier supplier = new Supplier();
        supplier.setSupplierId(0L);
        supplier.setCompanyName("companyName");
        supplier.setContactName("contactName");
        supplier.setContactTitle("contactTitle");
        supplier.setAddress("address");
        supplier.setCity("city");
        supplier.setRegion("region");
        supplier.setPostalCode("postalCode");
        supplier.setCountry("country");
        supplier.setPhone("phone");
        supplier.setFax(0);
        supplier.setHomePage("homePage");
        final List<Supplier> suppliers = Arrays.asList(supplier);
        when(mockService.getAll()).thenReturn(suppliers);

        // Run the test
        final ResponseEntity<List<Supplier>> result = supplierControllerUnderTest.getAllSuppliers();

        // Verify the results
    }

    @Test
    public void testGetAllSuppliers_SupplierServiceReturnsNoItems() {
        // Setup
        when(mockService.getAll()).thenReturn(Collections.emptyList());

        // Run the test
        final ResponseEntity<List<Supplier>> result = supplierControllerUnderTest.getAllSuppliers();

        // Verify the results
        assertThat(result).isEqualTo(ResponseEntity.ok(Collections.emptyList()));
    }

    @Test
    public void testGetSingleSupplier() {
        // Setup
        final ResponseEntity<?> expectedResult = new ResponseEntity<>(null, HttpStatus.OK);

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
        when(mockService.getSupplierById(0L)).thenReturn(supplier);

        // Run the test
        final ResponseEntity<?> result = supplierControllerUnderTest.getSingleSupplier(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testGetSingleSupplier_SupplierServiceReturnsAbsent() {
        // Setup
        final ResponseEntity<?> expectedResult = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        when(mockService.getSupplierById(0L)).thenReturn(Optional.empty());

        // Run the test
        final ResponseEntity<?> result = supplierControllerUnderTest.getSingleSupplier(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testCreateSupplier() {
        // Setup
        final Supplier supplier = new Supplier();
        supplier.setSupplierId(0L);
        supplier.setCompanyName("companyName");
        supplier.setContactName("contactName");
        supplier.setContactTitle("contactTitle");
        supplier.setAddress("address");
        supplier.setCity("city");
        supplier.setRegion("region");
        supplier.setPostalCode("postalCode");
        supplier.setCountry("country");
        supplier.setPhone("phone");
        supplier.setFax(0);
        supplier.setHomePage("homePage");

        final BindingResult bindingResult = null;
        final ResponseEntity<?> expectedResult = new ResponseEntity<>(null, HttpStatus.OK);

        // Configure SupplierService.save(...).
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
        when(mockService.save(any(Supplier.class))).thenReturn(supplier1);

        // Run the test
        final ResponseEntity<?> result = supplierControllerUnderTest.createSupplier(supplier, bindingResult);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testDeleteSupplier() {
        // Setup
        final ResponseEntity<?> expectedResult = new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

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
        when(mockService.getSupplierById(0L)).thenReturn(supplier);

        // Run the test
        final ResponseEntity<?> result = supplierControllerUnderTest.deleteSupplier(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockService).delete(0L);
    }

    @Test
    public void testDeleteSupplier_SupplierServiceGetSupplierByIdReturnsAbsent() {
        // Setup
        final ResponseEntity<?> expectedResult = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        when(mockService.getSupplierById(0L)).thenReturn(Optional.empty());

        // Run the test
        final ResponseEntity<?> result = supplierControllerUnderTest.deleteSupplier(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
