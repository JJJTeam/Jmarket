function dataSend() {
    alert("test")
    let data=$("#input").val();
    let data2=$("#input2").val();
    let AccountFormDTO={
        username:data,
        password:data2
    };

    $.ajax({
        url: "/api/account",
        data: AccountFormDTO,
        type:"POST",
    }).done(function (fragment) {
        $("#resultDiv").replaceWith(fragment);
    });
}
