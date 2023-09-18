package service;

import dto.exchangerate.ExchangeRateDto;

import java.util.List;

public interface ExchangeRateService {
    List<ExchangeRateDto> findAll();
    ExchangeRateDto findByCodePair(String baseCurrencyCode, String targetCurrencyCode);
}
