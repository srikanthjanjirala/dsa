package com.dsa.practice.util;

@FunctionalInterface
public interface DiscountCalculator {
    double apply(double amount);
}
