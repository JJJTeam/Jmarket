package com.jjjteam.jmarket.dto.payload.request;


import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import javax.validation.constraints.*;

@Getter
@Setter
public class SignUpRequest {
    @NotBlank
//    @Size(min = 3, max = 20)
    private String userid;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private Set<String> role;

    @NotBlank
//    @Size(min = 6, max = 40)
    private String password;

}
