package service.impl;

import dao.CurrencyDao;
import dto.currency.CreateCurrencyDto;
import dto.currency.CurrencyDto;
import exception.currency.CurrencyNotFoundException;
import exception.currency.CurrencyValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import model.Currency;
import service.CurrencyService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {
    private final CurrencyDao currencyDao;
    private final Validator validator;

    private void checkViolations(final Set<ConstraintViolation<Object>> violations) {
        if (!violations.isEmpty()) {
            throw new CurrencyValidationException(violations);
        }
    }

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

    @Override
    public CurrencyDto save(CreateCurrencyDto createCurrencyDto) {
        checkViolations(this.validator.validate(createCurrencyDto));
        final Currency currency = this.currencyDao.save(createCurrencyDto);
        return CurrencyDto.from(currency);
    }
}
