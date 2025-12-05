package com.michaelgarnica.fx_api.DTO;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record HolidayResponseDTO(
        LocalDate settlementDate
) {}
