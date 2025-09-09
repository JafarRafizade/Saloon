package com.example.project200.handlers;

import java.time.LocalDateTime;

public record ErrorMessage(
        LocalDateTime localDateTime,
        String message,
        String path
) {

}
