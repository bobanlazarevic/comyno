package com.bobanlazarevic.comynosender.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDto {
    @Size(min = 3, max = 100, message = "The length of message must be between 3 and 100 characters.")
    private String message;
}
