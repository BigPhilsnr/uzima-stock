package com.uzimahealth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.function.Consumer;

@Configuration
public class StockEventConsumer {

    @Autowired
    private StockEventService stockEventService;

    @Bean
    public Consumer<Map<String, Object>> stockEvents() {
        return eventData -> {
            String eventType = (String) eventData.get("eventType");

            System.out.println("Received stock event: " + eventType);

            switch (eventType) {
                case "PRESCRIPTION_DISPENSED":
                    stockEventService.handlePrescriptionDispensed(eventData);
                    break;
                case "STOCK_ADJUSTMENT":
                    stockEventService.handleStockAdjustment(eventData);
                    break;
                default:
                    System.out.println("Unknown event type: " + eventType);
            }
        };
    }
}