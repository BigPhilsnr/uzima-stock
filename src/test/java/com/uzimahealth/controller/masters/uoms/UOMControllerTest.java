package com.uzimahealth.controller.masters.uoms;

import com.uzimahealth.stock.UOM;
import com.uzimahealth.repository.UOMRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UOMControllerTest {

    @Mock
    private UOMRepository uomRepository;

    @InjectMocks
    private UOMController uomController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUOMs_ShouldReturnAllUOMs() {
        // Arrange
        UOM uom1 = new UOM();
        uom1.setUomName("PCS");

        UOM uom2 = new UOM();
        uom2.setUomName("BOX");

        List<UOM> uoms = Arrays.asList(uom1, uom2);
        when(uomRepository.findAll()).thenReturn(uoms);

        // Act
        ResponseEntity<List<UOM>> response = uomController.getAllUOMs();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(uomRepository, times(1)).findAll();
    }

    @Test
    void createUOM_ShouldReturnCreatedUOM() {
        // Arrange
        UOM uom = new UOM();
        uom.setUomName("KG");

        when(uomRepository.save(any(UOM.class))).thenReturn(uom);

        // Act
        ResponseEntity<UOM> response = uomController.createUOM(uom);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("KG", response.getBody().getUomName());
        verify(uomRepository, times(1)).save(uom);
    }
}