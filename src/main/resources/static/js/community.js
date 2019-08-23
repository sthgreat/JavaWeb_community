function post() {
    var articleId = $("#article_id").val();
    var content = $("#comment_content").val();
    $.ajax({
       type:"POST",
       url:"/comment",
        contentType:'application/json',
       data:JSON.stringify({
           "parentId":articleId,
           "content":content,
           "type":1
       }),
       success:function (response) {
           if(response.code===200){
               $("#comment_section").hide();
           }else {
               alert(response.message);
           }
           console.log(response)
       } ,
        dataType:"json"
    });
    console.log(content);
    console.log(articleId);
}