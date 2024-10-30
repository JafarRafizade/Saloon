package com.example.project200.Controllers;

import com.example.project200.requestDto.ServeReqDto;
import com.example.project200.responseDto.ServeResDto;
import com.example.project200.services.ServeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("service")
@RequiredArgsConstructor
public class ServeController {
    private final ServeService serveService;
    @PostMapping("/add-serve")
    public String addServe(@RequestBody ServeReqDto serveReqDto) {
        return serveService.addServe(serveReqDto);
    }

    @PutMapping("/update-serve")

    public String updateServe(@RequestBody ServeReqDto serveReqDto, @RequestParam Long id) {
        return serveService.updateServe(serveReqDto, id);
    }
    @GetMapping("/get-all-serves")
    public List<ServeResDto> getAllServes() {
        return serveService.getAllServes();
    }
    @DeleteMapping("/delete-serve")
    public String deleteServe(@RequestParam Long id) {
        return serveService.deleteServe(id);
    }

}
