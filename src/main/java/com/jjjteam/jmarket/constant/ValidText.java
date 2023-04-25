package com.jjjteam.jmarket.constant;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ValidText {

    checkEmail("checkEmail", true, "중복된 이메일입니다."),
    checkEmail2("checkEmail", false, "pass"),
    checkId("checkId", true, "중복된 아이디입니다."),
    checkId2("checkId", false, "pass"),
    phoneAuth2("phoneAuth", true, "pass"),
    phoneAuth("phoneAuth", false, "인증되지 않은 전화번호입니다.");

    private String dataName;
    private Boolean result;
    private String returnMessege;
    ValidText( String dataName, Boolean result ,String returnMessege) {
        this.dataName = dataName;
        this.result = result;
        this.returnMessege = returnMessege;
    }
    // 타입, 번호를 기준으로 식별자 필터링
    public static ValidText getVaild (String dataName, Boolean result) {
        return Arrays.stream(ValidText.values())
                .filter(x -> x.dataName.equals(dataName) && x.result == result)
                .findAny()
                .get();
//                .orElse(missingno);
    }
    // 필터링된 식별자의 이름 반환
    public static String getValidText (String dataname, Boolean result) {
        return getVaild(dataname, result).getReturnMessege();
    }
    public String getReturnMessege() {
        return this.returnMessege;
    }
}
