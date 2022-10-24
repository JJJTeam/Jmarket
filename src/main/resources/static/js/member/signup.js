
function signupFunction(){
    let dataParam = {
        "username": $("#username").val(),
        "email": $("#email").val(),
        "password": $("#password").val()
    };
    $.ajax({
        type:'POST',
        url:"/api/auth/signup",
        data: JSON.stringify(dataParam),
        contentType:"application/json;charset=utf=8"
    })
        .done(function(resp){
            alert(resp+"가입을 축하합니다.")
            location.href='/member/login'
        })
        .fail(function(e){
            alert(e+"수정실패")
        })
}



// var checkIdResult = false, checkPasswdResult = false;
//
// function checkId(idForm) {
//     var id = idForm.value;
//
//
//     var element = document.getElementById('checkIdResult');
//
//     var regex = /^[A-Za-z]+[A-Za-z0-9]{2,7}$/g;
//     var caseRegex = /[A-Za-z]/;
//     var digitRegex = /[0-9]/;
//
//
//     if(regex.exec(id)) {
//         var count = 0;
//         if(caseRegex.exec(id)) count++;
//         if(digitRegex.exec(id)) count++;
//
//         if(count == 2 ){
//             element.innerHTML = "사용 가능";
//             checkIdResult = true;
//         } else {
//             element.innerHTML = "사용 불가(영문자,숫자를 혼용하여 3~8글자 입력하세요)";
//             checkIdResult = false;
//         }
//     } else {
//         element.innerHTML = "사용 불가(영문자,숫자를 혼용하여 3~8글자 입력하세요)";
//         checkIdResult = false;
//     }
//
// }
//
// function checkPasswd(passwdForm) {
//     var passwd = passwdForm.value;
//
//     var element = document.getElementById('checkPasswdResult');
//
//     var lengthRegex = /^[A-Za-z0-9!@#$%]{2,7}$/;
//     var upperCaseRegex = /[A-Z]/;
//     var lowerCaseRegex = /[a-z]/;
//     var digitRegex = /[0-9]/;
//     var specRegex = /[!@#$%]/;
//
//     if(lengthRegex.exec(passwd)) {
//         var count = 0;
//         if(upperCaseRegex.exec(passwd)) count++;
//         if(lowerCaseRegex.exec(passwd)) count++;
//         if(digitRegex.exec(passwd)) count++;
//         if(specRegex.exec(passwd)) count++;
//
// // 			element.innerHTML = "사용 가능 " + count;
//
//         // 점수(count) 에 따른 안전도 출력
//         if(count == 4) {
//             element.innerHTML = "사용 가능(안전)";
//             checkPasswdResult = true; // 전역변수 true 로 변경
//         } else if(count == 3) {
//             element.innerHTML = "사용 가능(보통)";
//             checkPasswdResult = true; // 전역변수 true 로 변경
//         } else {
//             element.innerHTML = "사용 불가(영문자,숫자,특수문자(!@#$%)를 혼용하여 3~8글자 입력하세요)";
//             checkPasswdResult = false; // 전역변수 false 로 변경
//         }
//
//     } else {
//         element.innerHTML = "사용 불가(영문자,숫자,특수문자(!@#$%)를 혼용하여 3~8글자 입력하세요)";
//         checkPasswdResult = false; // 전역변수 false 로 변경
//     }
//
// }
//
// function checkPasswd2(passwdForm){
//     var passwd2 = passwdForm.value;
//
//     var element = document.getElementById('checkPasswdResult2');
//     if(document.fr.pass.value != document.fr.pass2.value){
//         element.innerHTML = "비밀번호 불일치";
//         checkPasswdResult2 = true;
//     } else {
//         element.innerHTML = "비밀번호 일치";
//         checkPasswdResult2 = false;
//     }
// }
//
// function checkPhone(phoneForm) {
//     var phone = phoneForm.value;
//
//
//     var element = document.getElementById('checkPhoneResult');
//
// // 		var regex = /^[0-9]*$/;
//     var regex = /^[0-9]{2,11}$/;
//     var rege= /^[0-9]{12,30}$/;
//
//
//
//     if(regex.exec(phone)) {
//         element.innerHTML = "사용 가능";
//         checkPhoneResult = true;
//     } else if(rege.exec(phone)){
//         element.innerHTML = "11자리 이하로 입력해주세요";
//         checkPhoneResult = false;
//     }
//
//     else {
//         element.innerHTML = "숫자만 입력하세요";
//         checkPhoneResult = false;
//     }
//
//
// // 		if(regex.exec(phone)) {
// // 			element.innerHTML = "사용 가능";
// // 			checkPhoneResult = true;
// // 		} else {
// // 			element.innerHTML = "숫자만 입력하세요";
// // 			checkPhoneResult = false;
// // 		}
//
// }
//
// // 아이디, 패스워드 정규표현식 체크 후 정상이면 true 리턴(submit), 아니면 false 리턴
// function check() {
//     if(checkIdResult && checkPasswdResult) {
//         return true;
//     } else {
//         alert('아이디 또는 패스워드 규칙 확인 필수!');
//         return false;
//     }
// }
//
// function openIdChk() {
//     if(document.fr.id.value==""){
//         alert('아이디를 입력해주세요')
//         return false;
//     } else {
//         window.name="parentForm";
//         window.open("dupCheckForm.mo",
//             "chkForm", "width=500, height=380, resizable = no, scrollbars = no");
//
//     }
// }
//
// function inputIdChk(){
//     document.userInfo.idDuplication.value ="idUncheck";
// }
//
