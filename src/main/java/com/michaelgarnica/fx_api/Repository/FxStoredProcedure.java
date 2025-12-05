package com.michaelgarnica.fx_api.Repository;

import com.michaelgarnica.fx_api.DTO.HolidayResponseDTO;

import java.time.LocalDate;

public interface FxStoredProcedure {
    HolidayResponseDTO calculateSettlementDate(String sourceCurrency, String destinyCurrency, String action, LocalDate transactionDate);
}
