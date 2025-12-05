package com.michaelgarnica.fx_api.DTO;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RequestDTO(
        @NotEmpty
        String sourceCurrency,
        @NotEmpty
        String destinyCurrency,
        @NotEmpty
        String action,
        @NotNull
        @FutureOrPresent
        LocalDate transactionDate
) {}
