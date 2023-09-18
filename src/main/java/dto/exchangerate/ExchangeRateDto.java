package dto.exchangerate;

import dto.currency.CurrencyDto;
import lombok.Value;
import model.ExchangeRate;

import java.math.BigDecimal;

@Value
public class ExchangeRateDto {
    int id;
    CurrencyDto baseCurrency;
    CurrencyDto targetCurrency;
    BigDecimal rate;

    public static ExchangeRateDto from(final ExchangeRate exchangeRate) {
        return new ExchangeRateDto(
                exchangeRate.getId(),
                CurrencyDto.from(exchangeRate.getBaseCurrency()),
                CurrencyDto.from(exchangeRate.getTargetCurrency()),
                exchangeRate.getRate()
        );
    }
}
