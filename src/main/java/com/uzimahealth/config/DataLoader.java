package com.uzimahealth.config;

import com.uzimahealth.model.User;
import com.uzimahealth.repository.*;
import com.uzimahealth.stock.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final ItemGroupRepository itemGroupRepository;
    private final WarehouseRepository warehouseRepository;
    private final UOMRepository uomRepository;
    private final BatchRepository batchRepository;
    private final SerialNoRepository serialNoRepository;
    private final StockEntryRepository stockEntryRepository;
    private final DeliveryNoteRepository deliveryNoteRepository;
    private final PurchaseReceiptRepository purchaseReceiptRepository;
    private final MaterialRequestRepository materialRequestRepository;
    private final StockReconciliationRepository stockReconciliationRepository;

    public DataLoader(UserRepository userRepository, ItemRepository itemRepository,
                      ItemGroupRepository itemGroupRepository, WarehouseRepository warehouseRepository,
                      UOMRepository uomRepository, BatchRepository batchRepository,
                      SerialNoRepository serialNoRepository, StockEntryRepository stockEntryRepository,
                      DeliveryNoteRepository deliveryNoteRepository, PurchaseReceiptRepository purchaseReceiptRepository,
                      MaterialRequestRepository materialRequestRepository, StockReconciliationRepository stockReconciliationRepository) {
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.itemGroupRepository = itemGroupRepository;
        this.warehouseRepository = warehouseRepository;
        this.uomRepository = uomRepository;
        this.batchRepository = batchRepository;
        this.serialNoRepository = serialNoRepository;
        this.stockEntryRepository = stockEntryRepository;
        this.deliveryNoteRepository = deliveryNoteRepository;
        this.purchaseReceiptRepository = purchaseReceiptRepository;
        this.materialRequestRepository = materialRequestRepository;
        this.stockReconciliationRepository = stockReconciliationRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Sample users
        User admin = userRepository.save(new User("admin", "password", "System Administrator"));
        User pharmacist = userRepository.save(new User("pharmacist", "password", "Pharmacist"));

        // Sample UOMs
        UOM tablets = uomRepository.save(new UOM("Tablets", false));
        UOM capsules = uomRepository.save(new UOM("Capsules", false));
        UOM bottles = uomRepository.save(new UOM("Bottles", true));

        // Sample Item Groups
        ItemGroup drugs = itemGroupRepository.save(new ItemGroup("Drugs", null, true, "Medical drugs and medications"));
        ItemGroup antibiotics = itemGroupRepository.save(new ItemGroup("Antibiotics", "Drugs", true, "Antibiotic medications"));

        // Sample Warehouses
        Warehouse pharmacyStore = warehouseRepository.save(new Warehouse("PHARM", "Pharmacy Store", false, null, "Uzima Health", "Pharmacy Account", "Store", false));
        Warehouse mainStore = warehouseRepository.save(new Warehouse("MAIN", "Main Store", false, null, "Uzima Health", "Main Account", "Store", false));

        // Sample Items
        Item paracetamol = itemRepository.save(new Item("ITM001", "Paracetamol 500mg", "Drugs", "Tablets", "Pain reliever",
                true, false, null, "PharmaCorp", "MediLab", 0.5, 0.5, 50, "365", true, true, false, null, false));
        Item amoxicillin = itemRepository.save(new Item("ITM002", "Amoxicillin 500mg", "Antibiotics", "Capsules", "Antibiotic",
                true, false, null, "MediLab", "PharmaCorp", 1.2, 1.2, 20, "180", true, true, false, null, false));

        // Sample Batches
        Batch batch1 = batchRepository.save(new Batch("BATCH001", paracetamol, "ITM001", LocalDate.of(2026, 12, 31), LocalDate.of(2024, 1, 1), "PharmaCorp", "Standard batch"));
        Batch batch2 = batchRepository.save(new Batch("BATCH002", amoxicillin, "ITM002", LocalDate.of(2026, 6, 30), LocalDate.of(2024, 2, 1), "MediLab", "Standard batch"));

        // Sample Serial Numbers
        SerialNo serial1 = serialNoRepository.save(new SerialNo("SN001", paracetamol, "ITM001", "Pharmacy Store", "BATCH001", "Active",
                "Purchase Receipt", "PR001", LocalDateTime.now(), null, null, null, "Uzima Health"));
        SerialNo serial2 = serialNoRepository.save(new SerialNo("SN002", amoxicillin, "ITM002", "Pharmacy Store", "BATCH002", "Active",
                "Purchase Receipt", "PR001", LocalDateTime.now(), null, null, null, "Uzima Health"));

        // Sample Stock Entry
        StockEntry stockEntry = stockEntryRepository.save(new StockEntry("STE001", "Material Receipt", "Stock receipt from supplier",
                null, "Pharmacy Store", LocalDateTime.now(), LocalDateTime.now(), false, "Uzima Health", null));

        // Sample Delivery Note
        DeliveryNote deliveryNote = deliveryNoteRepository.save(new DeliveryNote("DN001", "Uzima Health Center",
                LocalDateTime.now(), LocalDateTime.now(), "Uzima Health", false, null, "KES", 0.0, 0.0, 0.0,
                "Draft", null));

        // Sample Purchase Receipt
        PurchaseReceipt purchaseReceipt = purchaseReceiptRepository.save(new PurchaseReceipt("PR001", "MediLab Supplies",
                LocalDateTime.now(), LocalDateTime.now(), "Uzima Health", false, null, "KES", 0.0, 0.0, 0.0,
                "Completed", null));

        // Sample Material Request
        MaterialRequest materialRequest = materialRequestRepository.save(new MaterialRequest("MR001", "Purchase", "Open",
                LocalDateTime.now(), LocalDateTime.now().plusDays(7), "Uzima Health", null));

        // Sample Stock Reconciliation
        StockReconciliation reconciliation = stockReconciliationRepository.save(new StockReconciliation("REC001",
                "Pharmacy Store", LocalDateTime.now(), LocalDateTime.now(), "Uzima Health",
                "Monthly stock count", null));

        System.out.println("Stock management sample data loaded successfully!");
    }
}