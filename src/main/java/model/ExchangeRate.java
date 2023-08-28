package model;

import lombok.Value;

@Value
public class ExchangeRate {
    int id;
    int baseCurrencyId;
    int targetCurrencyId;
    float rate;
}
