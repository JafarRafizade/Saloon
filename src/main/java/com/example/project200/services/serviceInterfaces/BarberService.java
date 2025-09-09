package com.example.project200.services.serviceInterfaces;

import com.example.project200.requestDto.BarberCreateReqDto;
import com.example.project200.requestDto.BarberUpdateReqDto;
import com.example.project200.responseDto.BarberResDto;
import com.example.project200.responseDto.PaginationResDto;
import jakarta.transaction.Transactional;

import java.util.List;

public interface BarberService {
    String addBarber(BarberCreateReqDto barberRequestDto);
    String updateBarber(Long id, BarberUpdateReqDto barberRequestDto, String token);


    String deleteBarber(Long id, String token);
    List<BarberResDto> getAllBarber();
    BarberResDto getBarberById(Long id);
    List<BarberResDto> getBarberByFirstName(String firstName);
    List<BarberResDto> getBarberByLastName(String lastName);
    String getOnlineStatus(Long id);
    String updateStatus(Long id, Integer newStatus,String token);
    PaginationResDto getBarberByOnline(int page, int size);
    PaginationResDto searchEngine(String engine,int page, int size);
    List<BarberResDto> getBarberByBarberShop(Long id);

}
