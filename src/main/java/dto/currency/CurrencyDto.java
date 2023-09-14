package dto.currency;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("name") String fullName;
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
