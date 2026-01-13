package com.dsa.practice.service.impl;

import com.dsa.practice.service.PaymentService;
import org.springframework.stereotype.Component;

@Component("stripe")
public class StripePaymentService implements PaymentService {

    @Override
    public String pay(double amount){
        return "stripe payment of amount: " + amount + " processed successfully.";
    }
}
