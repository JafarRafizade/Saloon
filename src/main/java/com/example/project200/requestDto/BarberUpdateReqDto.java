package com.example.project200.requestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BarberUpdateReqDto {
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
    @NotNull(message = "Password cannot be null")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).+$",
            message = "Password must contain at least one letter and one number")
    private String password;
    @NotNull(message = "Number cannot be null")
    private String number;
    @NotNull(message = "Mail cannot be null")
    @Email
    private String mail;
    private List<Long> serviceId;
}



