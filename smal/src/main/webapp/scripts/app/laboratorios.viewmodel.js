window.app.viewmodel = new LaboratoriosViewModel();

function LaboratoriosViewModel() {
	var self = this;

	self.laboratorios = ko.observableArray([]);

	self.obterTodos = function() {
		window.app.notifyDebug("Obtendo todos...", "");
		$.ajax("/smal/json/laboratorio/obterTodos", {
			cache : false,
			type : "GET",
			contentType : "application/json",
			success : function(result) {
				if (result.sucesso) {
					window.app.notifyInfo("Listando:", JSON
							.stringify(result.mensagem));
					var mappedLaboratorios = $.map(result.mensagem, function(
							item) {
						return new ObterTodosResponse(item);
					});
					self.laboratorios(mappedLaboratorios);
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

	self.incluir = function(form) {
		var incluirRequest = {
			nome : form.nome.value
		};
		window.app.notifyDebug("Incluindo...", JSON.stringify(ko
				.toJSON(incluirRequest)));
		$.ajax("/smal/json/laboratorio/incluir", {
			cache : false,
			type : "POST",
			contentType : "application/json",
			data : ko.toJSON(incluirRequest),
			success : function(result) {
				if (result.sucesso) {
					window.app.notifyInfo("Incluido:", JSON.stringify(result));
					self.obterTodos();
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

	self.alterar = function(laboratorio) {
		window.app.notifyDebug("Alterando...", JSON.stringify(ko
				.toJSON(laboratorio)));
		$.ajax("/smal/json/laboratorio/alterar", {
			cache : false,
			type : "POST",
			contentType : "application/json",
			data : ko.toJSON(laboratorio),
			success : function(result) {
				if (result.sucesso) {
					window.app.notifyInfo("Alterado:", JSON.stringify(result));
					self.obterTodos();
				} else {
					window.app.notifyError("Erro na alteração:",
							result.mensagem);
				}
			},
			failure : function(result) {
				window.app.notifyError("Erro na alteraçaõ:", JSON
						.stringify(result));
				self.obterTodos();
			}
		});
	};

	self.excluir = function(laboratorio) {
		window.app.notifyDebug("Excluindo...", JSON.stringify(ko
				.toJSON(laboratorio)));
		$.ajax("/smal/json/laboratorio/excluir", {
			cache : false,
			type : "POST",
			contentType : "application/json",
			data : ko.toJSON(laboratorio),
			success : function(result) {
				if (result.sucesso) {
					window.app.notifyInfo("Excluido:", JSON.stringify(result));
					self.obterTodos();
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

function ObterTodosResponse(data) {
	this.id = ko.observable(data.id);
	this.nome = ko.observable(data.nome);
};
