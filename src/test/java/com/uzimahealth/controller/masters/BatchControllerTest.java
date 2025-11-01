package com.uzimahealth.controller.masters;

import com.uzimahealth.stock.Batch;
import com.uzimahealth.stock.SerialNo;
import com.uzimahealth.repository.BatchRepository;
import com.uzimahealth.repository.SerialNoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class BatchControllerTest {

    @Mock
    private BatchRepository batchRepository;

    @Mock
    private SerialNoRepository serialNoRepository;

    @InjectMocks
    private BatchController batchController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllBatches_ShouldReturnAllBatches() {
        // Arrange
        Batch batch1 = new Batch();
        batch1.setBatchId("BATCH001");

        Batch batch2 = new Batch();
        batch2.setBatchId("BATCH002");

        List<Batch> batches = Arrays.asList(batch1, batch2);
        when(batchRepository.findAll()).thenReturn(batches);

        // Act
        ResponseEntity<List<Batch>> response = batchController.getAllBatches();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(batchRepository, times(1)).findAll();
    }

    @Test
    void getBatchById_BatchExists_ShouldReturnBatch() {
        // Arrange
        Batch batch = new Batch();
        batch.setBatchId("BATCH001");
        batch.setItemCode("ITEM001");

        when(batchRepository.findByBatchId("BATCH001")).thenReturn(Optional.of(batch));

        // Act
        ResponseEntity<Batch> response = batchController.getBatchById("BATCH001");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("BATCH001", response.getBody().getBatchId());
        assertEquals("ITEM001", response.getBody().getItemCode());
        verify(batchRepository, times(1)).findByBatchId("BATCH001");
    }

    @Test
    void getBatchById_BatchNotFound_ShouldReturnNotFound() {
        // Arrange
        when(batchRepository.findByBatchId("NONEXISTENT")).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Batch> response = batchController.getBatchById("NONEXISTENT");

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(batchRepository, times(1)).findByBatchId("NONEXISTENT");
    }

    @Test
    void getBatchesByItem_ShouldReturnBatchesForItem() {
        // Arrange
        Batch batch1 = new Batch();
        batch1.setBatchId("BATCH001");
        batch1.setItemCode("ITEM001");

        Batch batch2 = new Batch();
        batch2.setBatchId("BATCH002");
        batch2.setItemCode("ITEM001");

        List<Batch> batches = Arrays.asList(batch1, batch2);
        when(batchRepository.findByItemCode("ITEM001")).thenReturn(batches);

        // Act
        ResponseEntity<List<Batch>> response = batchController.getBatchesByItem("ITEM001");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        assertEquals("ITEM001", response.getBody().get(0).getItemCode());
        verify(batchRepository, times(1)).findByItemCode("ITEM001");
    }

    @Test
    void createBatch_ShouldReturnCreatedBatch() {
        // Arrange
        Batch batch = new Batch();
        batch.setBatchId("BATCH001");
        batch.setItemCode("ITEM001");

        when(batchRepository.save(any(Batch.class))).thenReturn(batch);

        // Act
        ResponseEntity<Batch> response = batchController.createBatch(batch);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("BATCH001", response.getBody().getBatchId());
        verify(batchRepository, times(1)).save(batch);
    }

    @Test
    void updateBatch_BatchExists_ShouldReturnUpdatedBatch() {
        // Arrange
        Batch existingBatch = new Batch();
        existingBatch.setBatchId("BATCH001");
        existingBatch.setItemCode("ITEM001");

        Batch updatedBatch = new Batch();
        updatedBatch.setItemCode("ITEM002");

        when(batchRepository.findByBatchId("BATCH001")).thenReturn(Optional.of(existingBatch));
        when(batchRepository.save(any(Batch.class))).thenReturn(updatedBatch);

        // Act
        ResponseEntity<Batch> response = batchController.updateBatch("BATCH001", updatedBatch);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("ITEM002", response.getBody().getItemCode());
        verify(batchRepository, times(1)).findByBatchId("BATCH001");
        verify(batchRepository, times(1)).save(updatedBatch);
    }

    @Test
    void updateBatch_BatchNotFound_ShouldReturnNotFound() {
        // Arrange
        Batch updatedBatch = new Batch();
        updatedBatch.setItemCode("ITEM002");

        when(batchRepository.findByBatchId("NONEXISTENT")).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Batch> response = batchController.updateBatch("NONEXISTENT", updatedBatch);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(batchRepository, times(1)).findByBatchId("NONEXISTENT");
        verify(batchRepository, never()).save(any(Batch.class));
    }

    @Test
    void deleteBatch_BatchExists_ShouldReturnOk() {
        // Arrange
        Batch batch = new Batch();
        batch.setBatchId("BATCH001");

        when(batchRepository.findByBatchId("BATCH001")).thenReturn(Optional.of(batch));

        // Act
        ResponseEntity<Void> response = batchController.deleteBatch("BATCH001");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(batchRepository, times(1)).findByBatchId("BATCH001");
        verify(batchRepository, times(1)).delete(batch);
    }

    @Test
    void deleteBatch_BatchNotFound_ShouldReturnNotFound() {
        // Arrange
        when(batchRepository.findByBatchId("NONEXISTENT")).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Void> response = batchController.deleteBatch("NONEXISTENT");

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(batchRepository, times(1)).findByBatchId("NONEXISTENT");
        verify(batchRepository, never()).delete(any(Batch.class));
    }

    // Serial Number Tests
    @Test
    void getAllSerialNumbers_ShouldReturnAllSerialNumbers() {
        // Arrange
        SerialNo serial1 = new SerialNo();
        serial1.setSerialNo("SERIAL001");

        SerialNo serial2 = new SerialNo();
        serial2.setSerialNo("SERIAL002");

        List<SerialNo> serials = Arrays.asList(serial1, serial2);
        when(serialNoRepository.findAll()).thenReturn(serials);

        // Act
        ResponseEntity<List<SerialNo>> response = batchController.getAllSerialNumbers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(serialNoRepository, times(1)).findAll();
    }

    @Test
    void getSerialNumber_SerialExists_ShouldReturnSerial() {
        // Arrange
        SerialNo serial = new SerialNo();
        serial.setSerialNo("SERIAL001");
        serial.setItemCode("ITEM001");

        when(serialNoRepository.findBySerialNo("SERIAL001")).thenReturn(Optional.of(serial));

        // Act
        ResponseEntity<SerialNo> response = batchController.getSerialNumber("SERIAL001");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("SERIAL001", response.getBody().getSerialNo());
        verify(serialNoRepository, times(1)).findBySerialNo("SERIAL001");
    }

    @Test
    void getSerialNumber_SerialNotFound_ShouldReturnNotFound() {
        // Arrange
        when(serialNoRepository.findBySerialNo("NONEXISTENT")).thenReturn(Optional.empty());

        // Act
        ResponseEntity<SerialNo> response = batchController.getSerialNumber("NONEXISTENT");

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(serialNoRepository, times(1)).findBySerialNo("NONEXISTENT");
    }

    @Test
    void getSerialNumbersByItem_ShouldReturnSerialsForItem() {
        // Arrange
        SerialNo serial1 = new SerialNo();
        serial1.setSerialNo("SERIAL001");
        serial1.setItemCode("ITEM001");

        SerialNo serial2 = new SerialNo();
        serial2.setSerialNo("SERIAL002");
        serial2.setItemCode("ITEM001");

        List<SerialNo> serials = Arrays.asList(serial1, serial2);
        when(serialNoRepository.findByItemCode("ITEM001")).thenReturn(serials);

        // Act
        ResponseEntity<List<SerialNo>> response = batchController.getSerialNumbersByItem("ITEM001");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(serialNoRepository, times(1)).findByItemCode("ITEM001");
    }

    @Test
    void createSerialNumber_ShouldReturnCreatedSerial() {
        // Arrange
        SerialNo serial = new SerialNo();
        serial.setSerialNo("SERIAL001");
        serial.setItemCode("ITEM001");

        when(serialNoRepository.save(any(SerialNo.class))).thenReturn(serial);

        // Act
        ResponseEntity<SerialNo> response = batchController.createSerialNumber(serial);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("SERIAL001", response.getBody().getSerialNo());
        verify(serialNoRepository, times(1)).save(serial);
    }

    @Test
    void updateSerialNumber_SerialExists_ShouldReturnUpdatedSerial() {
        // Arrange
        SerialNo existingSerial = new SerialNo();
        existingSerial.setSerialNo("SERIAL001");
        existingSerial.setItemCode("ITEM001");

        SerialNo updatedSerial = new SerialNo();
        updatedSerial.setItemCode("ITEM002");

        when(serialNoRepository.findBySerialNo("SERIAL001")).thenReturn(Optional.of(existingSerial));
        when(serialNoRepository.save(any(SerialNo.class))).thenReturn(updatedSerial);

        // Act
        ResponseEntity<SerialNo> response = batchController.updateSerialNumber("SERIAL001", updatedSerial);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("ITEM002", response.getBody().getItemCode());
        verify(serialNoRepository, times(1)).findBySerialNo("SERIAL001");
        verify(serialNoRepository, times(1)).save(updatedSerial);
    }

    @Test
    void updateSerialNumber_SerialNotFound_ShouldReturnNotFound() {
        // Arrange
        SerialNo updatedSerial = new SerialNo();
        updatedSerial.setItemCode("ITEM002");

        when(serialNoRepository.findBySerialNo("NONEXISTENT")).thenReturn(Optional.empty());

        // Act
        ResponseEntity<SerialNo> response = batchController.updateSerialNumber("NONEXISTENT", updatedSerial);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(serialNoRepository, times(1)).findBySerialNo("NONEXISTENT");
        verify(serialNoRepository, never()).save(any(SerialNo.class));
    }

    @Test
    void deleteSerialNumber_SerialExists_ShouldReturnOk() {
        // Arrange
        SerialNo serial = new SerialNo();
        serial.setSerialNo("SERIAL001");

        when(serialNoRepository.findBySerialNo("SERIAL001")).thenReturn(Optional.of(serial));

        // Act
        ResponseEntity<Void> response = batchController.deleteSerialNumber("SERIAL001");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(serialNoRepository, times(1)).findBySerialNo("SERIAL001");
        verify(serialNoRepository, times(1)).delete(serial);
    }

    @Test
    void deleteSerialNumber_SerialNotFound_ShouldReturnNotFound() {
        // Arrange
        when(serialNoRepository.findBySerialNo("NONEXISTENT")).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Void> response = batchController.deleteSerialNumber("NONEXISTENT");

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(serialNoRepository, times(1)).findBySerialNo("NONEXISTENT");
        verify(serialNoRepository, never()).delete(any(SerialNo.class));
    }
}