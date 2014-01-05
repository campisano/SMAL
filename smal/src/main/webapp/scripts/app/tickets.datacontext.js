window.app = window.app || {};
window.app.datacontext = {
		listarChamados: function (ticketsList) {
			$.ajax("/smal/json/chamado/listarChamados", {
				cache : false,
				type : "GET",
				contentType : "application/json",
				success : function(result) {
					if (result.sucesso) {
						console.log(JSON.stringify(result.mensagem));
						ticketsList(result.mensagem);
					}
				},
				failure : function(result) {
					self.obterTodos();
				}
			});
		}
	};
