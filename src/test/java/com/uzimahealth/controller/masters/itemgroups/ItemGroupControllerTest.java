package com.uzimahealth.controller.masters.itemgroups;

import com.uzimahealth.stock.ItemGroup;
import com.uzimahealth.repository.ItemGroupRepository;
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

class ItemGroupControllerTest {

    @Mock
    private ItemGroupRepository itemGroupRepository;

    @InjectMocks
    private ItemGroupController itemGroupController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
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
        ResponseEntity<List<ItemGroup>> response = itemGroupController.getAllItemGroups();

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
        ResponseEntity<ItemGroup> response = itemGroupController.createItemGroup(group);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("New Group", response.getBody().getItemGroupName());
        verify(itemGroupRepository, times(1)).save(group);
    }
}