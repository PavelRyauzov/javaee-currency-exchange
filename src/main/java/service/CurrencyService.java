package service;

import dto.currency.CreateCurrencyDto;
import dto.currency.CurrencyDto;

import java.util.List;

public interface CurrencyService {
    List<CurrencyDto> findAll();
    CurrencyDto findByCode(String code);
    CurrencyDto save(CreateCurrencyDto createCurrencyDto);
}
