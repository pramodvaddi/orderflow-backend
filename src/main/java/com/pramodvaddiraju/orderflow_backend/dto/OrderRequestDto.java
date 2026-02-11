package com.pramodvaddiraju.orderflow_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class OrderRequestDto {

    @NotBlank(message = "Name cannot be null")
    private String customerName;
    @NotBlank(message = "Product cannot be null")
    private String productName;
    @NotNull(message = "Quantity cannot be null")
    private int quantity;

    public OrderRequestDto(){

    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
