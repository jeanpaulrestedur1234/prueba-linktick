package com.ejemplo.inventory.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.ejemplo.inventory.dto.ProductWithQuantityDTO;

@Component
public class ProductClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String productServiceUrl = "http://localhost:8081/api/products"; // Cambia por URL real

    public int getProductQuantity(Long productId , int currentQuantity) {
        System.out.println("[ProductClient] Iniciando consulta de cantidad para producto ID: " + productId);

        String url = productServiceUrl + "/" + productId;
        System.out.println("[ProductClient] URL construida para consulta: " + url);

        ProductResponse response = null;
        try {
            response = restTemplate.getForObject(url, ProductResponse.class);
            System.out.println("[ProductClient] Respuesta cruda recibida del servicio de productos:");
            if (response != null) {
                System.out.println("    -> " + response.toString());
            } else {
                System.out.println("    -> Respuesta nula");
            }
        } catch (Exception e) {
            System.out.println("[ProductClient] Error al consultar producto: " + e.getMessage());
        }

        if (response != null) {
            System.out.println("[ProductClient] Cantidad obtenida del producto: " + response.getQuantity());
            return response.getQuantity();
        } else {
            System.out.println("[ProductClient] No se recibió respuesta válida. Retornando 0.");
            return 0;
        }
    }

    // DTO interno para mapear respuesta, público y único
    public static class ProductResponse {
        private Long id;
        private String name;
        private int quantity;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }

        @Override
        public String toString() {
            return "{ \"id\": " + id + ", \"name\": \"" + name + "\", \"quantity\": " + quantity + " }";
        }
    }

    public ProductWithQuantityDTO getProductWithQuantity(Long productId, int currentQuantity) {
        String url = productServiceUrl + "/" + productId;

        ProductResponse response = null;
        try {
            response = restTemplate.getForObject(url, ProductResponse.class);
        } catch (Exception e) {
            System.out.println("[ProductClient] Error al consultar producto: " + e.getMessage());
        }

        if (response != null) {
            // Construimos DTO usando la info del producto remoto pero con cantidad del inventario local
            return new ProductWithQuantityDTO(response.getId(), response.getName(), currentQuantity);
        } else {
            System.out.println("[ProductClient] Producto no encontrado. Retornando objeto vacío con cantidad 0.");
            // Creamos DTO vacío sin intentar usar setters inexistentes
            return new ProductWithQuantityDTO(productId, "Desconocido", 0);
        }
    }

    // Método para crear un nuevo producto
    public ProductResponse createProduct(ProductResponse product) {
        return restTemplate.postForObject(productServiceUrl, product, ProductResponse.class);
    }

    // Método para actualizar un producto existente
    public void updateProduct(Long productId, ProductResponse product) {
        String url = productServiceUrl + "/" + productId;
        restTemplate.put(url, product);
    }

    // Método para eliminar un producto
    public void deleteProduct(Long productId) {
        String url = productServiceUrl + "/" + productId;
        restTemplate.delete(url);
    }
}
