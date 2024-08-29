package com.example.project200.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("service")
@RequiredArgsConstructor
public class ServiceController {
    @GetMapping("/get-services")
    public String getAllServices() {
        return null;
    }
    @PostMapping("/add-service")
    public String addServices(){
        return null;
    }
    @PutMapping("/update-service")
    public String updateService(){
        return null;
    }
    @DeleteMapping("/delete-service")
    public String deleteService(){
        return null;
    }


}
