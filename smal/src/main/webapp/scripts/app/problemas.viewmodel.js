window.app.viewmodel = new ProblemasViewModel();

function ProblemasViewModel() {
	var self = this;

	self.problemas = ko.observableArray([]);

	self.listarProblemas = function() {
		window.app.notifyDebug("Obtendo todos...", "");
		$.ajax("/smal/json/problema/listarProblemas", {
			cache : false,
			type : "GET",
			contentType : "application/json",
			success : function(result) {
				if (result.sucesso) {
					window.app.notifyInfo("Listando:", JSON
							.stringify(result.mensagem));
					var mappedObjects = $.map(result.mensagem, function(item) {
						return new ListarProblemasResponse(item);
					});
					self.problemas(mappedObjects);
				} else {
					window.app
							.notifyError("Erro na obtenção:", result.mensagem);
				}
			},
			failure : function(result) {
				window.app.notifyError("Erro na requisição:", JSON
						.stringify(result));
			}
		});
	};

	self.cadastrarProblema = function(form) {
		var request = {
			nome : form.nome.value
		};
		window.app.notifyDebug("Incluindo...", JSON.stringify(ko
				.toJSON(request)));
		$.ajax("/smal/json/problema/cadastrarProblema", {
			cache : false,
			type : "POST",
			contentType : "application/json",
			data : ko.toJSON(request),
			success : function(result) {
				if (result.sucesso) {
					window.app.notifyInfo("Incluido:", JSON.stringify(result));
					self.listarProblemas();
				} else {
					window.app
							.notifyError("Erro na inclusão:", result.mensagem);
				}
			},
			failure : function(result) {
				window.app.notifyError("Erro na inclusão:", JSON
						.stringify(result));
			}
		});
	};

	self.alterarProblema = function(problema) {
		var request = {
			id : problema.id,
			nome : problema.nome
		};
		window.app.notifyDebug("Alterando...", JSON.stringify(ko
				.toJSON(request)));
		$.ajax("/smal/json/problema/alterarProblema", {
			cache : false,
			type : "POST",
			contentType : "application/json",
			data : ko.toJSON(request),
			success : function(result) {
				if (result.sucesso) {
					window.app.notifyInfo("Alterado:", JSON.stringify(result));
					self.listarProblemas();
				} else {
					window.app.notifyError("Erro na alteração:",
							result.mensagem);
				}
			},
			failure : function(result) {
				window.app.notifyError("Erro na alteraçaõ:", JSON
						.stringify(result));
			}
		});
	};

	self.excluirProblema = function(problema) {
		var request = {
			id : problema.id
		};
		window.app.notifyDebug("Excluindo...", JSON.stringify(ko
				.toJSON(request)));
		$.ajax("/smal/json/problema/excluirProblema", {
			cache : false,
			type : "POST",
			contentType : "application/json",
			data : ko.toJSON(request),
			success : function(result) {
				if (result.sucesso) {
					window.app.notifyInfo("Excluido:", JSON.stringify(result));
					self.listarProblemas();
				} else {
					window.app
							.notifyError("Erro na exclusão:", result.mensagem);
				}
			},
			failure : function(result) {
				window.app.notifyError("Erro na exclusão:", JSON
						.stringify(result));
			}
		});
	};
};

function ListarProblemasResponse(data) {
	this.id = ko.observable(data.id);
	this.nome = ko.observable(data.nome);
};
