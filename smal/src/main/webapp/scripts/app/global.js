$( document ).ajaxStart(function() {
	
	
var request =  $.ajax({
    type: "POST",
    async:false,
    contentType: "application/json; charset=utf-8",
    url: "/smal/json/entrando/filtro",
    dataType: "json"
    
  });

request.done(function (msg){
	
	if(msg.sucesso){
		
		alert(msg.mensagem);
	
	}else{
	    
		alert(msg.mensagem);
	}
	
});
	
});

