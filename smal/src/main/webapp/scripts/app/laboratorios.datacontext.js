
window.app.datacontext = {
	obterTodos: function (list) {
		$.ajax("/smal/json/laboratorio/obterTodos", {
			cache : false,
			type : "GET",
			contentType : "application/json",
			success : function(result) {
				if (result.sucesso) {
					console.log(JSON.stringify(result.mensagem));
					list(result.mensagem);
				}
			},
			failure : function(result) {
				self.obterTodos();
			}
		});
	}
};

/*window.app = window.app || {};
window.app.datacontext = (function () {

    return {
        incluir: incluir,
        alterar: alterar,
        excluir: excluir
    };

    function incluir(laboratorio) {
        $.ajax("/smal/json/laboratorio/incluir", {
            cache: false,
            type: "POST",
            contentType: "application/json",
            data: ko.toJSON(laboratorio),
            success: function (result) {
                if (result.sucesso) {
                    self.notificar("Incluido:", JSON.stringify(result));
                    self.obterTodos();
                } else {
                    self.notificar("Erro na inclusão:", result.mensagem);
                }
            },
            failure: function (result) {
                self.notificar("Erro na inclusão:", JSON.stringify(result));
            }
        });
    };

    function alterar(laboratorio) {
        $.ajax("/smal/json/laboratorio/alterar", {
            cache: false,
            type: "POST",
            contentType: "application/json",
            data: ko.toJSON(laboratorio),
            success: function (result) {
                if (result.sucesso) {
                    self.notificar("Alterado:", JSON.stringify(result));
                    self.obterTodos();
                } else {
                    self.notificar("Erro na alteração:", result.mensagem);
                }
            },
            failure: function (result) {
                self.notificar("Erro na alteraçaõ:", JSON.stringify(result));
                self.obterTodos();
            }
        });
    };

    function excluir(laboratorio) {

    };
})*/