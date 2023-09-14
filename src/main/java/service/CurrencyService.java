package service;

import dto.CreateCurrencyDto;
import dto.CurrencyDto;

import java.util.List;

public interface CurrencyService {
    List<CurrencyDto> findAll();
    CurrencyDto findByCode(String code);
    CurrencyDto save(CreateCurrencyDto createCurrencyDto);
}
