package service.impl;

import dao.ExchangeRateDao;
import dto.exchangerate.ExchangeRateDto;
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
}
