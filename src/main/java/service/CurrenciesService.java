package service;

import dto.CurrencyDto;
import model.Currency;

import java.util.List;

public interface CurrenciesService {
    List<CurrencyDto> findAll();
    CurrencyDto findByCode(String code);
}
