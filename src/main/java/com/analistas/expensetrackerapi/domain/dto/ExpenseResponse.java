package com.analistas.expensetrackerapi.domain.dto;

import com.analistas.expensetrackerapi.domain.enums.EnumCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ExpenseResponse {

    private Long id;

    private EnumCategory category;

    private BigDecimal amount;

    private String description;

    private OffsetDateTime date;

}
