package com.dsa.practice.service.impl;

import com.dsa.practice.service.PaymentService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class PaypalPaymentService implements PaymentService {

    @Override
    public String pay(double amount) {
        return "Paid " + amount + " using PayPal.";
    }
}
