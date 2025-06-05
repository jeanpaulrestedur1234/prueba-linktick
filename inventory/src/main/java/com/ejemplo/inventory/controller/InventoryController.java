package com.ejemplo.inventory.controller;

import com.ejemplo.inventory.dto.ProductWithQuantityDTO;
import com.ejemplo.inventory.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ejemplo.inventory.dto.ProductWithQuantityDTO;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    // Consultar cantidad disponible
    @GetMapping("/quantity/{productId}")
    public ResponseEntity<ProductWithQuantityDTO> getQuantity(@PathVariable Long productId) {
        ProductWithQuantityDTO producto = inventoryService.getAvailableQuantity(productId);
        return ResponseEntity.ok(producto);
    }

    // Actualizar cantidad tras compra
    @PostMapping("/purchase/{productId}")
    public ResponseEntity<String> purchaseProduct(@PathVariable Long productId, @RequestParam int quantity) {
        try {
            inventoryService.updateQuantityAfterPurchase(productId, quantity);
            return ResponseEntity.ok("Compra realizada y stock actualizado");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
