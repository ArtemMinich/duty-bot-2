package com.dutybot2.dutybot2.dto;

import com.dutybot2.dutybot2.util.CadetStatus;
import com.dutybot2.dutybot2.util.Caste;

import java.io.Serializable;

/**
 * DTO for {@link com.dutybot2.dutybot2.model.Cadet}
 */
public record CadetDto(String lastName, int dutyDayCount, String chatId, CadetStatus status,
                       Caste caste) implements Serializable {
}