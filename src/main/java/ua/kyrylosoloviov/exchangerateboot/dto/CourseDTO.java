package ua.kyrylosoloviov.exchangerateboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CourseDTO {
    private BigDecimal rate;
    private String fromCurrencyCod;
    private String toCurrencyCod;
}
