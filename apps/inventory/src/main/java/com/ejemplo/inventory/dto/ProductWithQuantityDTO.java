package com.ejemplo.inventory.dto;

public class ProductWithQuantityDTO {
    private Long id;
    private String name;
    private int quantity;

    public ProductWithQuantityDTO() {}

    // Constructor con campos explícitos para no depender de ProductResponse
    public ProductWithQuantityDTO(Long id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
