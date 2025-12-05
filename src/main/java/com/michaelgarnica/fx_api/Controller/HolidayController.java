package com.michaelgarnica.fx_api.Controller;

import com.michaelgarnica.fx_api.DTO.HolidayResponseDTO;
import com.michaelgarnica.fx_api.DTO.RequestDTO;
import com.michaelgarnica.fx_api.Service.HolidayService;
import com.michaelgarnica.fx_api.Utils.Response;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "/api/v1/fx")
@CrossOrigin(origins = "*")
public class HolidayController {

    private final HolidayService holidayService;

    public HolidayController(HolidayService holidayService){
        this.holidayService = holidayService;
    }

    @PostMapping("/currencyTrade")
    public ResponseEntity<Response<HolidayResponseDTO>> currencySale (@RequestBody @Valid RequestDTO requestDTO){
        HolidayResponseDTO responseTransaction = holidayService.performTransaction(requestDTO);
        return ResponseEntity.ok(new Response<HolidayResponseDTO>("Transaction performed correctly!", LocalDate.now(), HttpStatus.OK, responseTransaction));
    }
}
