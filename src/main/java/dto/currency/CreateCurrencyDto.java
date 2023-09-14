package dto.currency;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;
import model.Currency;

/**
 * DTO for {@link Currency} entity
 */
@Value
public class CreateCurrencyDto {
    @NotBlank String name;
    @NotBlank @Size(min = 3, max = 3) String code;
    @NotBlank String sign;
}
