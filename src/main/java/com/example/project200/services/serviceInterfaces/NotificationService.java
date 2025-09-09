package com.example.project200.services.serviceInterfaces;

import com.example.project200.requestDto.NotificationReqDto;
import com.example.project200.responseDto.NotificationResDto;

public interface NotificationService {
    NotificationResDto notificationForCustomer(NotificationReqDto notificationReqDto);
}
