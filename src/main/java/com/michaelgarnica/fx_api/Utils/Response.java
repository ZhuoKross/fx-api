package com.michaelgarnica.fx_api.Utils;

import org.springframework.http.HttpStatus;

import java.time.LocalDate;

public record Response<T>(
        String message,
        LocalDate date,
        HttpStatus status,
        T data
) {}
