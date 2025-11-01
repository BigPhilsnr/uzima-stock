package com.uzimahealth.controller.masters;

import com.uzimahealth.stock.Item;
import com.uzimahealth.stock.ItemGroup;
import com.uzimahealth.stock.UOM;
import com.uzimahealth.repository.ItemRepository;
import com.uzimahealth.repository.ItemGroupRepository;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ItemControllerTest {

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private ItemGroupRepository itemGroupRepository;

    @Mock
    private UOMRepository uomRepository;

    @InjectMocks
    private ItemController itemController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllItems_ShouldReturnAllItems() {
        // Arrange
        Item item1 = new Item();
        item1.setItemCode("ITEM001");
        item1.setItemName("Test Item 1");

        Item item2 = new Item();
        item2.setItemCode("ITEM002");
        item2.setItemName("Test Item 2");

        List<Item> items = Arrays.asList(item1, item2);
        when(itemRepository.findAll()).thenReturn(items);

        // Act
        ResponseEntity<List<Item>> response = itemController.getAllItems();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        assertEquals("ITEM001", response.getBody().get(0).getItemCode());
        verify(itemRepository, times(1)).findAll();
    }

    @Test
    void getItemByCode_ItemExists_ShouldReturnItem() {
        // Arrange
        Item item = new Item();
        item.setItemCode("ITEM001");
        item.setItemName("Test Item");

        when(itemRepository.findByItemCode("ITEM001")).thenReturn(Optional.of(item));

        // Act
        ResponseEntity<Item> response = itemController.getItemByCode("ITEM001");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("ITEM001", response.getBody().getItemCode());
        assertEquals("Test Item", response.getBody().getItemName());
        verify(itemRepository, times(1)).findByItemCode("ITEM001");
    }

    @Test
    void getItemByCode_ItemNotFound_ShouldReturnNotFound() {
        // Arrange
        when(itemRepository.findByItemCode("NONEXISTENT")).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Item> response = itemController.getItemByCode("NONEXISTENT");

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(itemRepository, times(1)).findByItemCode("NONEXISTENT");
    }

    @Test
    void createItem_ShouldReturnCreatedItem() {
        // Arrange
        Item item = new Item();
        item.setItemCode("ITEM001");
        item.setItemName("New Item");

        when(itemRepository.save(any(Item.class))).thenReturn(item);

        // Act
        ResponseEntity<Item> response = itemController.createItem(item);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("ITEM001", response.getBody().getItemCode());
        assertEquals("New Item", response.getBody().getItemName());
        verify(itemRepository, times(1)).save(item);
    }

    @Test
    void updateItem_ItemExists_ShouldReturnUpdatedItem() {
        // Arrange
        Item existingItem = new Item();
        existingItem.setItemCode("ITEM001");
        existingItem.setItemName("Old Name");

        Item updatedItem = new Item();
        updatedItem.setItemName("Updated Name");

        when(itemRepository.findByItemCode("ITEM001")).thenReturn(Optional.of(existingItem));
        when(itemRepository.save(any(Item.class))).thenReturn(updatedItem);

        // Act
        ResponseEntity<Item> response = itemController.updateItem("ITEM001", updatedItem);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Updated Name", response.getBody().getItemName());
        verify(itemRepository, times(1)).findByItemCode("ITEM001");
        verify(itemRepository, times(1)).save(updatedItem);
    }

    @Test
    void updateItem_ItemNotFound_ShouldReturnNotFound() {
        // Arrange
        Item updatedItem = new Item();
        updatedItem.setItemName("Updated Name");

        when(itemRepository.findByItemCode("NONEXISTENT")).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Item> response = itemController.updateItem("NONEXISTENT", updatedItem);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(itemRepository, times(1)).findByItemCode("NONEXISTENT");
        verify(itemRepository, never()).save(any(Item.class));
    }

    @Test
    void deleteItem_ItemExists_ShouldReturnOk() {
        // Arrange
        Item item = new Item();
        item.setItemCode("ITEM001");

        when(itemRepository.findByItemCode("ITEM001")).thenReturn(Optional.of(item));

        // Act
        ResponseEntity<Void> response = itemController.deleteItem("ITEM001");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(itemRepository, times(1)).findByItemCode("ITEM001");
        verify(itemRepository, times(1)).delete(item);
    }

    @Test
    void deleteItem_ItemNotFound_ShouldReturnNotFound() {
        // Arrange
        when(itemRepository.findByItemCode("NONEXISTENT")).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Void> response = itemController.deleteItem("NONEXISTENT");

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(itemRepository, times(1)).findByItemCode("NONEXISTENT");
        verify(itemRepository, never()).delete(any(Item.class));
    }

    @Test
    void getAllItemGroups_ShouldReturnAllItemGroups() {
        // Arrange
        ItemGroup group1 = new ItemGroup();
        group1.setItemGroupName("Group 1");

        ItemGroup group2 = new ItemGroup();
        group2.setItemGroupName("Group 2");

        List<ItemGroup> groups = Arrays.asList(group1, group2);
        when(itemGroupRepository.findAll()).thenReturn(groups);

        // Act
        ResponseEntity<List<ItemGroup>> response = itemController.getAllItemGroups();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(itemGroupRepository, times(1)).findAll();
    }

    @Test
    void createItemGroup_ShouldReturnCreatedItemGroup() {
        // Arrange
        ItemGroup group = new ItemGroup();
        group.setItemGroupName("New Group");

        when(itemGroupRepository.save(any(ItemGroup.class))).thenReturn(group);

        // Act
        ResponseEntity<ItemGroup> response = itemController.createItemGroup(group);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("New Group", response.getBody().getItemGroupName());
        verify(itemGroupRepository, times(1)).save(group);
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
        ResponseEntity<List<UOM>> response = itemController.getAllUOMs();

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
        ResponseEntity<UOM> response = itemController.createUOM(uom);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("KG", response.getBody().getUomName());
        verify(uomRepository, times(1)).save(uom);
    }
}