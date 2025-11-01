package com.uzimahealth.service;

import com.uzimahealth.stock.Item;
import com.uzimahealth.stock.StockLedgerEntry;
import com.uzimahealth.stock.Warehouse;
import com.uzimahealth.repository.ItemRepository;
import com.uzimahealth.repository.StockLedgerEntryRepository;
import com.uzimahealth.repository.WarehouseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class StockEventServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private StockLedgerEntryRepository stockLedgerEntryRepository;

    @Mock
    private WarehouseRepository warehouseRepository;

    @InjectMocks
    private StockEventService stockEventService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handlePrescriptionDispensed_ItemAndWarehouseExist_ShouldCreateStockLedgerEntry() {
        // Arrange
        Map<String, Object> eventData = new HashMap<>();
        eventData.put("itemCode", "ITEM001");
        eventData.put("quantity", 10.0);
        eventData.put("warehouse", "Main Warehouse");
        eventData.put("user", "testuser");
        eventData.put("prescriptionNo", "PRE001");

        Item item = new Item();
        item.setItemCode("ITEM001");
        item.setStockUom("PCS");
        item.setValuationRate(100.0);

        Warehouse warehouse = new Warehouse();
        warehouse.setWarehouseName("Main Warehouse");

        when(itemRepository.findByItemCode("ITEM001")).thenReturn(Optional.of(item));
        when(warehouseRepository.findByWarehouseName("Main Warehouse")).thenReturn(Optional.of(warehouse));

        // Act
        stockEventService.handlePrescriptionDispensed(eventData);

        // Assert
        verify(itemRepository, times(1)).findByItemCode("ITEM001");
        verify(warehouseRepository, times(1)).findByWarehouseName("Main Warehouse");
        verify(stockLedgerEntryRepository, times(1)).save(any(StockLedgerEntry.class));
    }

    @Test
    void handlePrescriptionDispensed_ItemNotFound_ShouldNotCreateStockLedgerEntry() {
        // Arrange
        Map<String, Object> eventData = new HashMap<>();
        eventData.put("itemCode", "NONEXISTENT");
        eventData.put("quantity", 10.0);
        eventData.put("warehouse", "Main Warehouse");
        eventData.put("user", "testuser");
        eventData.put("prescriptionNo", "PRE001");

        Warehouse warehouse = new Warehouse();
        warehouse.setWarehouseName("Main Warehouse");

        when(itemRepository.findByItemCode("NONEXISTENT")).thenReturn(Optional.empty());
        when(warehouseRepository.findByWarehouseName("Main Warehouse")).thenReturn(Optional.of(warehouse));

        // Act
        stockEventService.handlePrescriptionDispensed(eventData);

        // Assert
        verify(itemRepository, times(1)).findByItemCode("NONEXISTENT");
        verify(warehouseRepository, times(1)).findByWarehouseName("Main Warehouse");
        verify(stockLedgerEntryRepository, never()).save(any(StockLedgerEntry.class));
    }

    @Test
    void handlePrescriptionDispensed_WarehouseNotFound_ShouldNotCreateStockLedgerEntry() {
        // Arrange
        Map<String, Object> eventData = new HashMap<>();
        eventData.put("itemCode", "ITEM001");
        eventData.put("quantity", 10.0);
        eventData.put("warehouse", "Nonexistent Warehouse");
        eventData.put("user", "testuser");
        eventData.put("prescriptionNo", "PRE001");

        Item item = new Item();
        item.setItemCode("ITEM001");
        item.setStockUom("PCS");
        item.setValuationRate(100.0);

        when(itemRepository.findByItemCode("ITEM001")).thenReturn(Optional.of(item));
        when(warehouseRepository.findByWarehouseName("Nonexistent Warehouse")).thenReturn(Optional.empty());

        // Act
        stockEventService.handlePrescriptionDispensed(eventData);

        // Assert
        verify(itemRepository, times(1)).findByItemCode("ITEM001");
        verify(warehouseRepository, times(1)).findByWarehouseName("Nonexistent Warehouse");
        verify(stockLedgerEntryRepository, never()).save(any(StockLedgerEntry.class));
    }

    @Test
    void handleStockAdjustment_InboundAdjustment_ShouldCreateStockLedgerEntry() {
        // Arrange
        Map<String, Object> eventData = new HashMap<>();
        eventData.put("itemCode", "ITEM001");
        eventData.put("quantity", 50.0);
        eventData.put("warehouse", "Main Warehouse");
        eventData.put("type", "in");
        eventData.put("adjustmentNo", "ADJ001");

        Item item = new Item();
        item.setItemCode("ITEM001");
        item.setStockUom("PCS");
        item.setValuationRate(100.0);

        Warehouse warehouse = new Warehouse();
        warehouse.setWarehouseName("Main Warehouse");

        when(itemRepository.findByItemCode("ITEM001")).thenReturn(Optional.of(item));
        when(warehouseRepository.findByWarehouseName("Main Warehouse")).thenReturn(Optional.of(warehouse));

        // Act
        stockEventService.handleStockAdjustment(eventData);

        // Assert
        verify(itemRepository, times(1)).findByItemCode("ITEM001");
        verify(warehouseRepository, times(1)).findByWarehouseName("Main Warehouse");
        verify(stockLedgerEntryRepository, times(1)).save(any(StockLedgerEntry.class));
    }

    @Test
    void handleStockAdjustment_OutboundAdjustment_ShouldCreateStockLedgerEntry() {
        // Arrange
        Map<String, Object> eventData = new HashMap<>();
        eventData.put("itemCode", "ITEM001");
        eventData.put("quantity", 25.0);
        eventData.put("warehouse", "Main Warehouse");
        eventData.put("type", "out");
        eventData.put("adjustmentNo", "ADJ002");

        Item item = new Item();
        item.setItemCode("ITEM001");
        item.setStockUom("PCS");
        item.setValuationRate(100.0);

        Warehouse warehouse = new Warehouse();
        warehouse.setWarehouseName("Main Warehouse");

        when(itemRepository.findByItemCode("ITEM001")).thenReturn(Optional.of(item));
        when(warehouseRepository.findByWarehouseName("Main Warehouse")).thenReturn(Optional.of(warehouse));

        // Act
        stockEventService.handleStockAdjustment(eventData);

        // Assert
        verify(itemRepository, times(1)).findByItemCode("ITEM001");
        verify(warehouseRepository, times(1)).findByWarehouseName("Main Warehouse");
        verify(stockLedgerEntryRepository, times(1)).save(any(StockLedgerEntry.class));
    }

    @Test
    void handleStockAdjustment_ItemNotFound_ShouldNotCreateStockLedgerEntry() {
        // Arrange
        Map<String, Object> eventData = new HashMap<>();
        eventData.put("itemCode", "NONEXISTENT");
        eventData.put("quantity", 25.0);
        eventData.put("warehouse", "Main Warehouse");
        eventData.put("type", "out");
        eventData.put("adjustmentNo", "ADJ002");

        Warehouse warehouse = new Warehouse();
        warehouse.setWarehouseName("Main Warehouse");

        when(itemRepository.findByItemCode("NONEXISTENT")).thenReturn(Optional.empty());
        when(warehouseRepository.findByWarehouseName("Main Warehouse")).thenReturn(Optional.of(warehouse));

        // Act
        stockEventService.handleStockAdjustment(eventData);

        // Assert
        verify(itemRepository, times(1)).findByItemCode("NONEXISTENT");
        verify(warehouseRepository, times(1)).findByWarehouseName("Main Warehouse");
        verify(stockLedgerEntryRepository, never()).save(any(StockLedgerEntry.class));
    }

    @Test
    void handleStockAdjustment_WarehouseNotFound_ShouldNotCreateStockLedgerEntry() {
        // Arrange
        Map<String, Object> eventData = new HashMap<>();
        eventData.put("itemCode", "ITEM001");
        eventData.put("quantity", 25.0);
        eventData.put("warehouse", "Nonexistent Warehouse");
        eventData.put("type", "out");
        eventData.put("adjustmentNo", "ADJ002");

        Item item = new Item();
        item.setItemCode("ITEM001");
        item.setStockUom("PCS");
        item.setValuationRate(100.0);

        when(itemRepository.findByItemCode("ITEM001")).thenReturn(Optional.of(item));
        when(warehouseRepository.findByWarehouseName("Nonexistent Warehouse")).thenReturn(Optional.empty());

        // Act
        stockEventService.handleStockAdjustment(eventData);

        // Assert
        verify(itemRepository, times(1)).findByItemCode("ITEM001");
        verify(warehouseRepository, times(1)).findByWarehouseName("Nonexistent Warehouse");
        verify(stockLedgerEntryRepository, never()).save(any(StockLedgerEntry.class));
    }
}