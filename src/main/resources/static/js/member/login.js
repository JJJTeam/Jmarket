$("#btnUpdate").click(function(){
    var dataParam = {
        "id":  $("#id").val(),
        "password":  $("#password").val()
    }
    $.ajax({
        type:'POST',
        url:"/update/",
        data: JSON.stringify(dataParam),
        contentType:"application/json;charset=utf=8"
    })
        .done(function(resp){
            alert(resp+"수정성공")
            location.href='/list'
        })
        .fail(function(e){
            alert(e+"수정실패")
        })
})