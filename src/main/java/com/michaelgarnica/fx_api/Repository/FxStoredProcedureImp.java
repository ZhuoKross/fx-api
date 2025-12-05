package com.michaelgarnica.fx_api.Repository;

import com.michaelgarnica.fx_api.DTO.HolidayResponseDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class FxStoredProcedureImp implements FxStoredProcedure{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public HolidayResponseDTO calculateSettlementDate(String sourceCurrency, String destinyCurrency, String action, LocalDate transactionDate) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("calculate_settlement_date");
        query.registerStoredProcedureParameter("i_source_currency", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("i_destiny_currency", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("i_date_transaction", LocalDate.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("i_action", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("o_settlement_date", LocalDate.class, ParameterMode.OUT);

        query.setParameter("i_source_currency", sourceCurrency);
        query.setParameter("i_destiny_currency", destinyCurrency);
        query.setParameter("i_date_transaction", transactionDate);
        query.setParameter("i_action", action);

        query.execute();

        LocalDate settlementDate = (LocalDate) query.getOutputParameterValue("o_settlement_date");

        return HolidayResponseDTO.builder()
                .settlementDate(settlementDate)
                .build();
    }
}
