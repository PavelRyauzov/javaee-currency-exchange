package dto;

import lombok.Value;
import model.Currency;

/**
 * DTO for {@link Currency} entity
 */
@Value
public class CurrencyDto {
    int id;
    String code;
    String fullName;
    String sign;

    public static CurrencyDto from(final Currency currency) {
        return new CurrencyDto(
                currency.getId(),
                currency.getCode(),
                currency.getFullName(),
                currency.getSign()
        );
    }
}
