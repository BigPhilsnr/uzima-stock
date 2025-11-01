package com.uzimahealth.stock;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void testDefaultConstructor() {
        // Act
        Item item = new Item();

        // Assert
        assertNotNull(item);
        assertNull(item.getId());
        assertNull(item.getItemCode());
        assertNull(item.getItemName());
        assertTrue(item.isStockItem());
        assertFalse(item.isHasVariants());
        assertTrue(item.isPurchaseItem());
        assertTrue(item.isSalesItem());
        assertFalse(item.isInspectionRequired());
        assertFalse(item.isDisabled());
    }

    @Test
    void testParameterizedConstructor() {
        // Arrange
        QualityInspectionTemplate template = new QualityInspectionTemplate();
        LocalDateTime now = LocalDateTime.now();

        // Act
        Item item = new Item("ITEM001", "Test Item", "Test Group", "PCS", "Test Description",
                true, false, null, "Test Brand", "Test Manufacturer",
                100.0, 90.0, 10, "365", true, true, false, template, false);

        // Assert
        assertEquals("ITEM001", item.getItemCode());
        assertEquals("Test Item", item.getItemName());
        assertEquals("Test Group", item.getItemGroup());
        assertEquals("PCS", item.getStockUom());
        assertEquals("Test Description", item.getDescription());
        assertTrue(item.isStockItem());
        assertFalse(item.isHasVariants());
        assertEquals("Test Brand", item.getBrand());
        assertEquals("Test Manufacturer", item.getManufacturer());
        assertEquals(100.0, item.getStandardRate());
        assertEquals(90.0, item.getValuationRate());
        assertEquals(10, item.getMinOrderQty());
        assertEquals("365", item.getShelfLifeInDays());
        assertTrue(item.isPurchaseItem());
        assertTrue(item.isSalesItem());
        assertFalse(item.isInspectionRequired());
        assertEquals(template, item.getQualityInspectionTemplate());
        assertFalse(item.isDisabled());
        assertNotNull(item.getCreation());
        assertNotNull(item.getModified());
    }

    @Test
    void testSettersAndGetters() {
        // Arrange
        Item item = new Item();
        LocalDateTime testTime = LocalDateTime.of(2025, 1, 1, 12, 0);

        // Act & Assert
        item.setId(1L);
        assertEquals(1L, item.getId());

        item.setItemCode("TEST001");
        assertEquals("TEST001", item.getItemCode());

        item.setItemName("Test Item Name");
        assertEquals("Test Item Name", item.getItemName());

        item.setItemGroup("Test Group");
        assertEquals("Test Group", item.getItemGroup());

        item.setStockUom("BOX");
        assertEquals("BOX", item.getStockUom());

        item.setDescription("Test Description");
        assertEquals("Test Description", item.getDescription());

        item.setStockItem(false);
        assertFalse(item.isStockItem());

        item.setHasVariants(true);
        assertTrue(item.isHasVariants());

        item.setVariantOf("PARENT001");
        assertEquals("PARENT001", item.getVariantOf());

        item.setBrand("Test Brand");
        assertEquals("Test Brand", item.getBrand());

        item.setManufacturer("Test Manufacturer");
        assertEquals("Test Manufacturer", item.getManufacturer());

        item.setStandardRate(150.0);
        assertEquals(150.0, item.getStandardRate());

        item.setValuationRate(140.0);
        assertEquals(140.0, item.getValuationRate());

        item.setMinOrderQty(5);
        assertEquals(5, item.getMinOrderQty());

        item.setShelfLifeInDays("180");
        assertEquals("180", item.getShelfLifeInDays());

        item.setPurchaseItem(false);
        assertFalse(item.isPurchaseItem());

        item.setSalesItem(false);
        assertFalse(item.isSalesItem());

        item.setInspectionRequired(true);
        assertTrue(item.isInspectionRequired());

        QualityInspectionTemplate template = new QualityInspectionTemplate();
        item.setQualityInspectionTemplate(template);
        assertEquals(template, item.getQualityInspectionTemplate());

        item.setDisabled(true);
        assertTrue(item.isDisabled());

        item.setCreation(testTime);
        assertEquals(testTime, item.getCreation());

        item.setModified(testTime);
        assertEquals(testTime, item.getModified());
    }

    @Test
    void testBooleanProperties() {
        // Arrange
        Item item = new Item();

        // Act & Assert - Test default values
        assertTrue(item.isStockItem());
        assertFalse(item.isHasVariants());
        assertTrue(item.isPurchaseItem());
        assertTrue(item.isSalesItem());
        assertFalse(item.isInspectionRequired());
        assertFalse(item.isDisabled());

        // Test setting to opposite values
        item.setStockItem(false);
        item.setHasVariants(true);
        item.setPurchaseItem(false);
        item.setSalesItem(false);
        item.setInspectionRequired(true);
        item.setDisabled(true);

        assertFalse(item.isStockItem());
        assertTrue(item.isHasVariants());
        assertFalse(item.isPurchaseItem());
        assertFalse(item.isSalesItem());
        assertTrue(item.isInspectionRequired());
        assertTrue(item.isDisabled());
    }

    @Test
    void testNumericProperties() {
        // Arrange
        Item item = new Item();

        // Act
        item.setStandardRate(200.50);
        item.setValuationRate(180.75);
        item.setMinOrderQty(25);

        // Assert
        assertEquals(200.50, item.getStandardRate());
        assertEquals(180.75, item.getValuationRate());
        assertEquals(25, item.getMinOrderQty());
    }

    @Test
    void testStringProperties() {
        // Arrange
        Item item = new Item();

        // Act
        item.setItemCode("CODE123");
        item.setItemName("Name Test");
        item.setItemGroup("Group Test");
        item.setStockUom("KG");
        item.setDescription("Description Test");
        item.setVariantOf("VARIANT123");
        item.setBrand("Brand Test");
        item.setManufacturer("Manufacturer Test");
        item.setShelfLifeInDays("730");

        // Assert
        assertEquals("CODE123", item.getItemCode());
        assertEquals("Name Test", item.getItemName());
        assertEquals("Group Test", item.getItemGroup());
        assertEquals("KG", item.getStockUom());
        assertEquals("Description Test", item.getDescription());
        assertEquals("VARIANT123", item.getVariantOf());
        assertEquals("Brand Test", item.getBrand());
        assertEquals("Manufacturer Test", item.getManufacturer());
        assertEquals("730", item.getShelfLifeInDays());
    }
}