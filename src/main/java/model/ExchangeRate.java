package model;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class ExchangeRate {
    int id;
    int baseCurrencyId;
    int targetCurrencyId;
    BigDecimal rate;
}
