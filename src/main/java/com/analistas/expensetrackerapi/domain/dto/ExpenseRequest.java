package com.analistas.expensetrackerapi.domain.dto;

import com.analistas.expensetrackerapi.domain.enums.EnumCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@NoArgsConstructor
@Getter
@Setter
public class ExpenseRequest {

    @NotNull
    private EnumCategory category;

    @Positive
    @NotNull
    private BigDecimal amount;

    @NotBlank
    private String description;

    @NotNull
    private OffsetDateTime date;
}
