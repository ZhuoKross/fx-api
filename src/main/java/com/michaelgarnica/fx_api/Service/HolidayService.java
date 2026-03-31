package com.michaelgarnica.fx_api.Service;

import com.michaelgarnica.fx_api.DTO.HolidayResponseDTO;
import com.michaelgarnica.fx_api.DTO.RequestDTO;
import com.michaelgarnica.fx_api.Repository.FxStoredProcedureImp;
import com.michaelgarnica.fx_api.Repository.HolidayRepository;
import org.springframework.stereotype.Service;

@Service
public class HolidayService {
    private final HolidayRepository holidayRepository;
    private final FxStoredProcedureImp fxStoredProcedureImp;

    public HolidayService(HolidayRepository holidayRepository, FxStoredProcedureImp fxStoredProcedureImp) {
        this.holidayRepository = holidayRepository;
        this.fxStoredProcedureImp = fxStoredProcedureImp;
    }

    public HolidayResponseDTO performTransaction(RequestDTO requestDTO) {
        return fxStoredProcedureImp.calculateSettlementDate(requestDTO.sourceCurrency(), requestDTO.destinyCurrency(),
                requestDTO.action(), requestDTO.transactionDate());
    }
}
