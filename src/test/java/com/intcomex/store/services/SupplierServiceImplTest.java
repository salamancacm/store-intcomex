package com.intcomex.store.services;

import com.intcomex.store.models.entity.Supplier;
import com.intcomex.store.repositories.SupplierRepository;
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
public class SupplierServiceImplTest {

    @Mock
    private SupplierRepository mockRepository;

    @InjectMocks
    private SupplierServiceImpl supplierServiceImplUnderTest;

    @Test
    public void testGetAll() {
        // Setup
        // Configure SupplierRepository.findAll(...).
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
        final Iterable<Supplier> suppliers = Arrays.asList(supplier);
        when(mockRepository.findAll()).thenReturn(suppliers);

        // Run the test
        final List<Supplier> result = supplierServiceImplUnderTest.getAll();

        // Verify the results
    }

    @Test
    public void testGetAll_SupplierRepositoryReturnsNoItems() {
        // Setup
        when(mockRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Supplier> result = supplierServiceImplUnderTest.getAll();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    public void testGetSupplierById() {
        // Setup
        // Configure SupplierRepository.findById(...).
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
        when(mockRepository.findById(0L)).thenReturn(supplier);

        // Run the test
        final Optional<Supplier> result = supplierServiceImplUnderTest.getSupplierById(0L);

        // Verify the results
    }

    @Test
    public void testGetSupplierById_SupplierRepositoryReturnsAbsent() {
        // Setup
        when(mockRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final Optional<Supplier> result = supplierServiceImplUnderTest.getSupplierById(0L);

        // Verify the results
        assertThat(result).isEmpty();
    }

    @Test
    public void testSave() {
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

        // Configure SupplierRepository.save(...).
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
        when(mockRepository.save(any(Supplier.class))).thenReturn(supplier1);

        // Run the test
        final Supplier result = supplierServiceImplUnderTest.save(supplier);

        // Verify the results
    }

    @Test
    public void testDelete() {
        // Setup
        // Run the test
        supplierServiceImplUnderTest.delete(0L);

        // Verify the results
        verify(mockRepository).deleteById(0L);
    }
}
