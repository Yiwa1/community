function readMessage(e){
    let id = e.getAttribute("data-id");
    debugger;
    console.log(id);
    $.ajax(
        {
        type:"POST",
            url:"/readMessage/"+id,
            success: function (response){
            console.log(response);
            }
        }
    )
}