package dto.currency;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Value;
import model.Currency;

/**
 * DTO for {@link Currency} entity
 */
@Value
@JsonPropertyOrder({"id", "name", "code", "sign"})
public class CurrencyDto {
    int id;
    String code;
    String name;
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
