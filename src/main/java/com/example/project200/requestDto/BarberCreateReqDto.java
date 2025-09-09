package com.example.project200.requestDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BarberCreateReqDto {
    @NotNull(message = "Username cannot be null")
    @Pattern(
            regexp = "^(?!_)(?!.*_$)[a-zA-Z0-9_]{3,16}$",
            message = "Username must be 3-16 characters long, can only contain letters, numbers, and underscores, and cannot start or end with an underscore."
    )
    private String username;
    @NotNull(message = "Firstname cannot be null")
    private String firstName;
    @NotNull(message = "Lastname cannot be null")
    private String lastName;
    @NotNull(message = "Number cannot be null")
    private String number;
    private Long barberShopId;

}
