package service;

import dto.exchangerate.ExchangeRateDto;

import java.util.List;

public interface ExchangeRateService {
    List<ExchangeRateDto> findAll();
}
