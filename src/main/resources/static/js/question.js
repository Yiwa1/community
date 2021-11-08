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
             window.open("https://github.com/login/oauth/authorize?client_id=723ca7c1206fcfbff6cb&redirect_uri=http://localhost:80/callback&scop=user");
             window.localStorage.setItem("closeable","true");
         }
      }else {
          alert(response.msg);
      }

    },
    complete:function (){
        $("#comment-main").load("http://localhost:80/question/"+id+" #comment-origin");
    },
    dataType:"json"
    })
}

function subCommentShow(e){

    let id = e.getAttribute("data-id");
    //二级评论开合状态
    let collapse = e.getAttribute("data-collapse");
    if(collapse){
        //二级评论状态为展开,将其关闭
        $("#subComment-"+id).removeClass("show");
        e.removeAttribute("data-collapse");
        $("#comment-comment-img-"+id).removeClass("active");
    }else {
        if($("#subComment-"+id).children().length===1){
            //没有查询
            $.ajax({
                type:"POST",
                url:"http://localhost:80/subComment/"+id,
                dataType: "json",
                success: function (response){
                    $.each(response,function (index,subcomment){
                        var subComment=$("<div/>",{
                            "class":"media sub-comment"
                        }).append($("<img/>",{
                            "style":"width:36px;height: 36px;margin-left:7px;",
                            "alt":"头像走丢了",
                            "src":subcomment.creator.avatarUrl
                        }));
                        var clickButton=$("<button/>",{
                            "class":"btn btn-light like-img"
                        }).append($("<img/>",{
                            "src": "/bootstrap/font/hand-thumbs-up.svg",
                            "style":"margin-bottom: 3px;margin-top: 3px;"
                        }));
                        var clickImg=$("<div/>",{
                            "class":"click-img"
                        }).append(clickButton);
                        var mediaBody=$("<div/>",{
                            "class": "media-body"
                        }).append($("<h6/>",{
                            "class":"questionInfo",
                            "style": "margin-left: 10px;margin-bottom: 0;margin-top: -3px",
                            "html": subcomment.creator.name
                        })).append($("<h6/>",{
                            "class":"comment-content",
                            "html":subcomment.content
                        })).append($("<span/>",{
                            "class":"questionInfo",
                            "style":"float: right",
                            "html":moment(subcomment.gmtCreate).format('YYYY-MM-DD')
                        })).append(clickImg)
                            .append($("<hr/>",{
                                class:"divide"
                            }));
                        subComment.append(mediaBody);
                        $("#subComment-"+id).prepend(subComment);
                    });

                }

            });
        }
        //二级评论状态为关闭,将其展开
        $("#subComment-"+id).addClass("show");
        e.setAttribute("data-collapse","true");
        $("#comment-comment-img-"+id).addClass("active");

    }
}

function subComment(e){
    let id = e.getAttribute("data-id");
    let content = $("#subComment-content-"+id).val();
    let creator = $("#commenter_id").val();
    console.log(content);

    $.ajax({
        type:"POST",
        contentType: "application/json",
        url:"http://localhost:80/comment",
        data: JSON.stringify(
            {
            "type":1,
            "parentId":id,
            "likeCount":0,
            "content":content,
            "creator": creator
        }),
        dataType:"json",
        success: function (response){
            if(response.status==200){
                $("#subComment-content-"+id).innerText="";
            }else if(response.status==1314){
                var isAccepted=confirm(response.msg);
                if(isAccepted){
                    window.open("https://github.com/login/oauth/authorize?client_id=723ca7c1206fcfbff6cb&redirect_uri=http://localhost:80/callback&scop=user");
                    window.localStorage.setItem("closeable","true");
                }
            }else {
                alert(response.msg);
            }
        },
        complete:function (){
            location.reload();
            $("#subComment-section-"+id).scrollIntoView();
        }
    })
}