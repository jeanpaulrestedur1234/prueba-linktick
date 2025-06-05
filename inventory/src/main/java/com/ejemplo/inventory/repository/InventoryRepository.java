package com.ejemplo.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ejemplo.inventory.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    // JpaRepository ya incluye métodos básicos
}
