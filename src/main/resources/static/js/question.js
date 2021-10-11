function comment (){
    let id = $("#question_id").val();
    let content = $("#comment_content").val();
    let creator = $("#commenter_id").val();
$.ajax({
  type:"POST",
    contentType:"application/json",
    url:"/comment",
    data:JSON.stringify(
        {
            "type":0,
            "parentId":id,
            "likeCount":0,
            "content":content,
            "creator":creator
        }
    ),
    success: function (response){
      if(response.status==200){
          $("#comment-section").hide();
      }else if(response.status==1314){
         var isAccepted=confirm(response.msg);
         if(isAccepted){
             window.open("https://github.com/login/oauth/authorize?client_id=723ca7c1206fcfbff6cb&redirect_uri=http://localhost:8080/callback&scop=user");
             window.localStorage.setItem("closeable","true");
         }
      }else {
          alert(response.msg);
      }

    },
    dataType:"json"
    })
}