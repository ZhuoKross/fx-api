package com.michaelgarnica.fx_api.Config;

import com.michaelgarnica.fx_api.DTO.NagerApiDateObjectDTO;
import com.michaelgarnica.fx_api.Entity.Holiday;
import com.michaelgarnica.fx_api.Repository.HolidayRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cglib.core.Local;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.List;

@Component
public class Seeders implements CommandLineRunner {

    private HolidayRepository holidayRepository;

    public Seeders(HolidayRepository holidayRepository) {
        this.holidayRepository = holidayRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (holidayRepository.count() == 0) {
            insertHolidays();
        }
    }

    public void insertHolidays() {
        WebClient webClient = WebClient.create("https://date.nager.at/api/v3/publicholidays/");
        ZoneId zoneId = ZoneId.systemDefault();
        List<NagerApiDateObjectDTO> colombia2025Holidays = webClient.get()
                .uri("/2025/CO")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<NagerApiDateObjectDTO>>() {
                })
                .block();
        List<NagerApiDateObjectDTO> canada2025Holidays = webClient.get()
                .uri("/2025/CA")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<NagerApiDateObjectDTO>>() {
                })
                .block();
        List<NagerApiDateObjectDTO> unitedStates2025Holidays = webClient.get()
                .uri("/2025/US")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<NagerApiDateObjectDTO>>() {
                })
                .block();
        List<NagerApiDateObjectDTO> colombia2026Holidays = webClient.get()
                .uri("/2026/CO")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<NagerApiDateObjectDTO>>() {
                })
                .block();
        List<NagerApiDateObjectDTO> canada2026Holidays = webClient.get()
                .uri("/2026/CA")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<NagerApiDateObjectDTO>>() {
                })
                .block();
        List<NagerApiDateObjectDTO> unitedStates2026Holidays = webClient.get()
                .uri("/2026/US")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<NagerApiDateObjectDTO>>() {
                })
                .block();

        List<Holiday> colombia2025HolidaysToSave = colombia2025Holidays.stream()
                .map((holidayDTO) -> {
                    Instant instant = Instant.from(holidayDTO.date().toInstant());
                    LocalDate formattedDate = instant.atZone(zoneId).toLocalDate();
                    return Holiday.builder()
                            .name(holidayDTO.name())
                            .date(formattedDate)
                            .countryCode(holidayDTO.countryCode())
                            .build();
                })
                .toList();
        List<Holiday> canada2025HolidaysToSave = canada2025Holidays.stream()
                .map((holidayDTO) -> {
                    Instant instant = Instant.from(holidayDTO.date().toInstant());
                    LocalDate formattedDate = instant.atZone(zoneId).toLocalDate();
                    return Holiday.builder()
                            .name(holidayDTO.name())
                            .date(formattedDate)
                            .countryCode(holidayDTO.countryCode())
                            .build();
                })
                .toList();
        List<Holiday> unitedStates2025HolidaysToSave = unitedStates2025Holidays.stream()
                .map((holidayDTO) -> {
                    Instant instant = Instant.from(holidayDTO.date().toInstant());
                    LocalDate formattedDate = instant.atZone(zoneId).toLocalDate();
                    return Holiday.builder()
                            .name(holidayDTO.name())
                            .date(formattedDate)
                            .countryCode(holidayDTO.countryCode())
                            .build();
                })
                .toList();
        List<Holiday> colombia2026HolidaysToSave = colombia2026Holidays.stream()
                .map((holidayDTO) ->{
                    Instant instant = Instant.from(holidayDTO.date().toInstant());
                    LocalDate formattedDate = instant.atZone(zoneId).toLocalDate();
                    return Holiday.builder()
                            .name(holidayDTO.name())
                            .date(formattedDate)
                            .countryCode(holidayDTO.countryCode())
                            .build();
                })
                .toList();
        List<Holiday> canada2026HolidaysToSave = canada2026Holidays.stream()
                .map((holidayDTO) ->{
                    Instant instant = Instant.from(holidayDTO.date().toInstant());
                    LocalDate formattedDate = instant.atZone(zoneId).toLocalDate();
                    return Holiday.builder()
                            .name(holidayDTO.name())
                            .date(formattedDate)
                            .countryCode(holidayDTO.countryCode())
                            .build();
                })
                .toList();
        List<Holiday> unitedStates2026HolidaysToSave = unitedStates2026Holidays.stream()
                .map((holidayDTO) ->{
                    Instant instant = Instant.from(holidayDTO.date().toInstant());
                    LocalDate formattedDate = instant.atZone(zoneId).toLocalDate();
                    return Holiday.builder()
                            .name(holidayDTO.name())
                            .date(formattedDate)
                            .countryCode(holidayDTO.countryCode())
                            .build();
                })
                .toList();



        Holiday european2025ChristmasHoliday = Holiday.builder().name("Christmas Day").date(LocalDate.of(2025, Month.DECEMBER, 25)).countryCode("EUROPE").build();
        Holiday european2025BoxingHoliday = Holiday.builder().name("Boxing Holiday").date(LocalDate.of(2025, Month.DECEMBER, 26)).countryCode("EUROPE").build();
        Holiday european2026ChristmasHoliday = Holiday.builder().name("Christmas Day").date(LocalDate.of(2025, Month.DECEMBER, 25)).countryCode("EUROPE").build();
        Holiday european2026BoxingHoliday = Holiday.builder().name("Boxing Holiday").date(LocalDate.of(2025, Month.DECEMBER, 26)).countryCode("EUROPE").build();
        Holiday european2026NewYearDayHoliday = Holiday.builder().name("New Year's Day").date(LocalDate.of(2026, Month.JANUARY, 1)).countryCode("EUROPE").build();
        Holiday european2026GoodFridayHoliday = Holiday.builder().name("Good Friday").date(LocalDate.of(2026, Month.APRIL, 3)).countryCode("EUROPE").build();
        Holiday european2026EasterMondayHoliday = Holiday.builder().name("Easter Monday").date(LocalDate.of(2026, Month.APRIL, 6)).countryCode("EUROPE").build();
        Holiday european2026LabourDayHoliday = Holiday.builder().name("Labour Day").date(LocalDate.of(2026, Month.MAY, 1)).countryCode("EUROPE").build();

        holidayRepository.saveAll(List.of(european2025ChristmasHoliday,
                european2025BoxingHoliday,
                european2026ChristmasHoliday,
                european2026BoxingHoliday,
                european2026NewYearDayHoliday,
                european2026GoodFridayHoliday,
                european2026EasterMondayHoliday,
                european2026LabourDayHoliday
        ));
        holidayRepository.saveAll(colombia2025HolidaysToSave);
        holidayRepository.saveAll(canada2025HolidaysToSave);
        holidayRepository.saveAll(unitedStates2025HolidaysToSave);
        holidayRepository.saveAll(colombia2026HolidaysToSave);
        holidayRepository.saveAll(canada2026HolidaysToSave);
        holidayRepository.saveAll(unitedStates2026HolidaysToSave);

    }
}
