package model;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class ExchangeRate {
    int id;
    Currency baseCurrency;
    Currency targetCurrency;
    BigDecimal rate;
}
