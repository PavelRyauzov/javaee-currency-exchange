package service.impl;

import dao.ExchangeRateDao;
import dto.exchangerate.ExchangeRateDto;
import exception.exchangerate.ExchangeRateNotFoundException;
import lombok.RequiredArgsConstructor;
import service.ExchangeRateService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {
    private final ExchangeRateDao exchangeRateDao;

    @Override
    public List<ExchangeRateDto> findAll() {
        return this.exchangeRateDao.findAll()
                .stream()
                .map(ExchangeRateDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public ExchangeRateDto findByCodePair(String baseCurrencyCode, String targetCurrencyCode) {
        return this.exchangeRateDao.findByCodePair(baseCurrencyCode, targetCurrencyCode)
                .map(ExchangeRateDto::from)
                .orElseThrow(() ->
                        new ExchangeRateNotFoundException(
                                String.format("Exchange rate with this code pair '%s''%s' not found",
                                        baseCurrencyCode,
                                        targetCurrencyCode)
                        )
                );
    }
}
