package com.example.project200.controllers;

import com.example.project200.requestDto.NotificationReqDto;
import com.example.project200.responseDto.NotificationResDto;
import com.example.project200.services.serviceInterfaces.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("notification")
public class NotificationController {
    private final NotificationService notificationService;


    @PostMapping("customer")
    public ResponseEntity<NotificationResDto> notificationForCustomer(@RequestBody NotificationReqDto notificationReqDto){
        return ResponseEntity.ok(notificationService.notificationForCustomer(notificationReqDto));

    }
}
