package com.analistas.expensetrackerapi.domain.mapper;

import com.analistas.expensetrackerapi.domain.dto.ExpenseRequest;
import com.analistas.expensetrackerapi.domain.dto.ExpenseResponse;
import com.analistas.expensetrackerapi.domain.entity.Expense;
import com.analistas.expensetrackerapi.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class ExpenseMapper {

    public Expense toEntity(ExpenseRequest expenseRequest, User user) {
        return new Expense(
                expenseRequest.getCategory(),
                expenseRequest.getAmount(),
                expenseRequest.getDescription(),
                expenseRequest.getDate(),
                user
        );
    }

    public ExpenseResponse toResponse(Expense expense) {
        return new ExpenseResponse(
                expense.getId(),
                expense.getCategory(),
                expense.getAmount(),
                expense.getDescription(),
                expense.getDate()
        );
    }
}
