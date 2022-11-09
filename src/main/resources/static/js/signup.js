
function checkId(){
    let dataParam = {
        "userId": $("#userid").val()
    };
    $.ajax({
            type:'GET',
            url:"/api/checkId",
            data: JSON.stringify(dataParam),
            contentType:"application/json;charset=utf=8"
        })
        .done(function(resp){
            alert("가입을 축하합니다.")
            console.log(resp)
        })
        .fail(function(e){
            alert(e+"수정실패")
        })
}


// $(document).ready(function() {
//     $("#checkId").click(function() {
//         $.ajax({
//             type:'GET',
//             url:"/api/checkId",
//             data: JSON.stringify({
//                 "userId": $("#userid").val()
//             }),
//             contentType:"application/json;charset=utf=8"
//         })



        // let mid = $("#mid").val();
        // $.ajax({
        //     // url : "/api/checkId/" + mid,
        //     dataType : "json",
        //     success : function(result) {
        //         if (result) {
        //             $("#idcheck").html("사용중인 아이디입니다.");
        //         } else {
        //             $("#idcheck").html("사용가능한 아이디입니다.");
        //         }
        //     }
        // });
//     });
// });
//
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