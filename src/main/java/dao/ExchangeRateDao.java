package dao;

import model.ExchangeRate;

import java.util.List;
import java.util.Optional;

public interface ExchangeRateDao {
    List<ExchangeRate> findAll();
    Optional<ExchangeRate> findByCodePair(String baseCurrencyCode, String targetCurrencyCode);
}
