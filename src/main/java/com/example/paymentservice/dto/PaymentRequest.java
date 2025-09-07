package com.example.paymentservice.dto;

import lombok.Data;

@Data
public class PaymentRequest {
    private Double amount;
    private String description;
    private Long userId;
}