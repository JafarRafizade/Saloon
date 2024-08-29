package com.example.project200.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/availability")
@RequiredArgsConstructor
public class AvailabilityController {
    @PutMapping("update-avai")
    public String updateAvailability() {
        return null;
    }
}
