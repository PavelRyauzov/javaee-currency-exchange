package service.impl;

import dao.CurrencyDao;
import dto.CurrencyDto;
import exception.currency.CurrencyNotFoundException;
import lombok.RequiredArgsConstructor;
import service.CurrencyService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {
    private final CurrencyDao currencyDao;

    @Override
    public List<CurrencyDto> findAll() {
        return this.currencyDao.findAll()
                .stream()
                .map(CurrencyDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public CurrencyDto findByCode(String code) {
        return this.currencyDao.findByCode(code)
                .map(CurrencyDto::from)
                .orElseThrow(() ->
                    new CurrencyNotFoundException(String.format("Currency with this code '%s' not found", code))
                );
    }
}