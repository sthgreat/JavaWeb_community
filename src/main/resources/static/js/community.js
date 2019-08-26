function post() {
    var articleId = $("#article_id").val();
    var content = $("#comment_content").val();
    if(!content){
        alert("不能回复空内容！");
        return;
    }
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
           debugger;
           if(response.code==200){
               window.location.reload();
           }else {
               if(response.code==2003){
                   var isAccepted = confirm(response.message);
                   if(isAccepted){
                       window.open("https://github.com/login/oauth/authorize?client_id=93c26d7d2df671df7b7a&redirect_uri=http://localhost:8080/callback&scope=user&state=1");
                       window.localStorage.setItem("closable",true);
                   }
               }else {
                   alert(response.message);
               }
           }
           console.log(response)
       } ,
        dataType:"json"
    });
    console.log(content);
    console.log(articleId);
}