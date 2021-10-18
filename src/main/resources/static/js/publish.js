function show(){
    $("#tag-section").show();
}

function getTag(tag){
    let innerHTML = tag.innerHTML;
    let tagContent = $("#taginput").val();

    if(tagContent){
        //已经有标签了
        if(tagContent.indexOf(innerHTML)===-1){
            //此标签之前未添加
            $("#taginput").val(tagContent+","+innerHTML);
        }
    }else {
        $("#taginput").val(innerHTML);
    }

}