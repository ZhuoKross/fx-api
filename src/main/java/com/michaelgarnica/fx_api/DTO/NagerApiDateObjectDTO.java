package com.michaelgarnica.fx_api.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

public record NagerApiDateObjectDTO(
   Date date,
   String localName,
   String name,
   String countryCode,
   boolean fixed,
   @JsonIgnore
   String counties,
   @JsonIgnore
   Integer launchYear,
   List<String> types
) {}
