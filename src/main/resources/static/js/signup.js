
$("#userid").keyup(function(e) {
    $("#idCheck").html(checkCount($("#userid").val(),4,20))

    // var content = $(this).val();
    // $("#textLengthCheck").val("(" + content.length + "/ 200)"); //실시간 글자수 카운팅
    // if (content.length > 200) {
    //     Alert("최대 200자까지 입력 가능합니다.");
    //     $(this).val(content.substring(0, 200));
    //     $('#textLengthCheck').html("(200 / 최대 200자)");
    // }
});
function checkCount(text,a,b){
    if (text.length<a&& b==0){
        // alert(a+"자 이상 "+b+"자 이하로 입력해주시기 바랍니다.")
        return a+"자 이상으로 입력해주시기 바랍니다.";
    } else if (text.length<a||text.length>b){
        // alert(a+"자 이상으로 입력해주시기 바랍니다.")
        return a+"자 이상 "+b+"자 이하로 입력해주시기 바랍니다.";
        ;
    } else return ""
}



function checkDoubleId(){
    checkCount($("#userid").val(),4,20)
    $.ajax({
            type:'GET',
            url:"/api/checkId",
            data: { userId: $("#userid").val() }
        })
        .done(function(resp){
            resp ? $("#idCheck").html("사용중인 아이디입니다.") : $("#idCheck").html("사용가능한 아이디입니다.");
        })
        .fail(function(e){
            alert("error")
        })
}
function checkDoubleEmail(){
    $.ajax({
        type:'GET',
        url:"/api/checkEmail",
        data: { email: $("#email").val() }
    })
        .done(function(resp){
            resp ? $("#emailCheck").html("사용중인 이메일입니다.") : $("#emailCheck").html("사용가능한 이메일입니다.");
        })
        .fail(function(e){
            alert("error")
        })
}
function phoneAuth(){
    $.ajax({
        type:'POST',
        url:"/api/phoneAuth",
        data: JSON.stringify($("#userPhoneNumber").val()),
        dataType:"json",
        contentType:"application/json;charset=utf=8"
    })
        .done(function(resp){
            resp ? $("#phoneCheck").html("전화테스트1") : $("#phoneCheck").html("문자를 발송하였습니다.");
        })
        .fail(function(e){
            alert("error")
        })
}
function phoneAuthOk(){
    $.ajax({
        type:'POST',
        url:"/api/phoneAuthOk",
        data:'code='+$('#code').val()
        // dataType:"json",
        // contentType:"application/json;charset=utf=8"
    })
        .done(function(resp){
            resp ? $("#phoneCheck").html("잘못입력하셨습니다.") : $("#phoneCheck").html("인증완료");
        })
        .fail(function(e){
            alert("인증을 다시 시도해 주세요")
        })
}


// function check() {
//     var name = document.getElementsByName("mname")[0];
//     var id = document.getElementsByName("mid")[0];
//     var pwd1 = document.getElementsByName("mpw")[0];
//     var pwd2 = document.getElementsByName("pwd2")[0];
//     var email = document.getElementsByName("memail")[0];
//     var ereg = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
//     var birth = document.getElementsByName("mbirth")[0];
//     var phone = document.getElementsByName("mphone")[0];
//     if (name.value.trim() == "") {
//         alert("이름을 입력하세요.");
//         name.focus();
//         return false;
//     }
//     if (id.value.trim() == "") {
//         alert("아이디를 입력하세요.");
//         id.focus();
//         return false;
//     }
//     if (pwd1.value.trim() == "") {
//         alert("비밀번호를 입력하세요.");
//         pwd.focus();
//         return false;
//     }
//     if (pwd1.value.length<8 || pwd1.value.length>15) {
//         alert("비밀번호는 8-15자리의 영문/숫자를 함께 입력해주세요.");
//         pwd1.focus();
//         return false;
//     }
//     if (pwd2.value.trim() == "") {
//         alert("비밀번호 확인을 입력하세요.");
//         pwd.focus();
//         return false;
//     }
//     if (pwd1.value != pwd2.value) {
//         alert("새로운 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
//         pwd1.value = "";
//         pwd2.value = "";
//         pwd1.focus();
//         return false;
//     }
//     if (email.value.trim() == "") {
//         alert("이메일을 입력하세요.");
//         email.focus();
//         return false;
//     }
//     if (!ereg.test(email.value)) {
//         alert("이메일형식에 맞게 입력하세요.");
//         email.focus();
//         return false;
//     }
//     if (birth.value.trim() == "") {
//         alert("생년월일을 입력하세요.");
//         birth.focus();
//         return false;
//     }
//     if (phone.value.trim() == "") {
//         alert("휴대폰번호를 입력하세요.");
//         phone.focus();
//         return false;
//     }
//     if (phone.value.indexOf("-") != -1) {
//         alert("휴대폰번호에 숫자만 입력해주세요.");
//         phone.focus();
//         return false;
//     }
//     if (phone.value.length<10 || phone.value.length>12) {
//         alert("휴대폰번호는 11자리를 입력해주세요.");
//         pwd1.focus();
//         return false;
//     }
//     alert("회원가입에 성공했습니다!");
//     return true;
// }