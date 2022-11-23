package com.jjjteam.jmarket.dto;

import com.jjjteam.jmarket.model.User;
import com.jjjteam.jmarket.model.UserAddress;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserAddressDTO {
    private Long id;
    private String postCode;
    private String address;
    private String addressDetail;
    private String addressPhoneNumber;
    private String person;
    private Boolean defaultAddress;
    private User user;

    public UserAddress toEntity(User user){
        UserAddress userAddress = UserAddress.builder()
                .id(id)
                .postCode(postCode)
                .address(address)
                .addressDetail(addressDetail)
                .addressPhoneNumber(addressPhoneNumber)
                .person(person)
                .defaultAddress(defaultAddress)
                .user(user)
                .build();
        return userAddress;
    }

    public UserAddressDTO(UserAddress userAddress) {
        id = userAddress.getId();
        address=userAddress.getAddress();
        addressDetail=userAddress.getAddressDetail();
        person=userAddress.getPerson();
        defaultAddress=userAddress.getDefaultAddress();
        user=userAddress.getUser();
        postCode=userAddress.getPostCode();
        addressPhoneNumber=userAddress.getAddressPhoneNumber();
//        orderItems = order.getOrderItems().stream()
//                .map(OrderItemDto::new)
//                .collect(Collectors.toList());
    }



}
