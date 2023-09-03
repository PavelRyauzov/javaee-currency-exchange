package service;

import dto.CurrencyDto;

import java.util.List;

public interface CurrencyService {
    List<CurrencyDto> findAll();
    CurrencyDto findByCode(String code);
}
