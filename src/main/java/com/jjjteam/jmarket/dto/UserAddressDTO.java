package com.jjjteam.jmarket.dto;

import com.jjjteam.jmarket.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAddressDTO {

    private Long id;
    private String address;
    private String addressDetail;
    private String addressPhoneNumber;
    private String person;
    private Boolean defaultAddress;
    private User user;



    public UserAddressDTO() {
    }
}
