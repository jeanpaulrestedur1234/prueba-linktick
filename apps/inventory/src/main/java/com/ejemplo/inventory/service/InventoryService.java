package com.ejemplo.inventory.service;

import com.ejemplo.inventory.client.ProductClient;
import com.ejemplo.inventory.model.Inventory;
import com.ejemplo.inventory.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ejemplo.inventory.dto.ProductWithQuantityDTO;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductClient productClient;

    public InventoryService(InventoryRepository inventoryRepository, ProductClient productClient) {
        this.inventoryRepository = inventoryRepository;
        this.productClient = productClient;
    }

    public ProductWithQuantityDTO getAvailableQuantity(Long productId) {
        Inventory inventory = inventoryRepository.findById(productId)
                .orElse(new Inventory(productId, 0));
        int quantity = inventory.getQuantity();
        System.out.println("Cantidad disponible en inventario para producto " + productId + ": " + quantity);

        // Traemos el producto con la cantidad del inventario local
        ProductWithQuantityDTO producto = productClient.getProductWithQuantity(productId, quantity);

        return producto;
    }


    // Actualizar cantidad tras una compra
    @Transactional
    public void updateQuantityAfterPurchase(Long productId, int quantityBought) {
        Inventory inventory = inventoryRepository.findById(productId)
                .orElse(new Inventory(productId, 0));

        int nuevaCantidad = inventory.getQuantity() - quantityBought;
        if (nuevaCantidad < 0) {
            throw new IllegalArgumentException("Cantidad insuficiente en inventario");
        }

        inventory.setQuantity(nuevaCantidad);
        inventoryRepository.save(inventory);

        System.out.println("Inventario actualizado para producto " + productId + ". Nueva cantidad: " + nuevaCantidad);
    }
    
}
