package com.jjjteam.jmarket.dto;

import com.jjjteam.jmarket.model.User;
import com.jjjteam.jmarket.model.UserAddress;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Getter
//@Setter
@Data
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
