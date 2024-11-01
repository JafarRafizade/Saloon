package com.example.project200.handlers;

import java.time.LocalDateTime;

public record ErrorResponse(
         LocalDateTime timestamp,
         int status,
         String error,
         String message
) {

}
