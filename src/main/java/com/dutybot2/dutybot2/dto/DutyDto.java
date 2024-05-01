package com.dutybot2.dutybot2.dto;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.dutybot2.dutybot2.model.Duty}
 */
public record DutyDto(LocalDate dutyDate, String terkaCadetLastName,
                      String cubarCadetLastName) implements Serializable {
}