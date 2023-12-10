package com.bobanlazarevic.comynosender.controller;

import com.bobanlazarevic.comynosender.dto.MessageDto;
import com.bobanlazarevic.comynosender.service.Producer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Validated
public class MessageController {

    private final Producer producer;

    @Operation(summary = "Send message to the queue.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Message is accepted."),
            @ApiResponse(responseCode = "400", description = "Invalid message: the length of message must be between 3 and 100 characters.")
    })
    @PostMapping("/publish")
    public ResponseEntity<String> handleMessage(@Valid @RequestBody MessageDto messageDto) {
        producer.handleMessage(messageDto);
        return ResponseEntity.ok("Message is accepted...");
    }

}
