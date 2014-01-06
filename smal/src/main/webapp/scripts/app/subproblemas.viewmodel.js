window.app.viewmodel = new SubproblemasViewModel();

function SubproblemasViewModel() {
	var self = this;

	self.subproblemas = ko.observableArray([]);

	self.listarSubproblemas = function() {
		window.app.notifyDebug("Obtendo todos...", "");
		$.ajax("/smal/json/subproblema/listarSubproblemas", {
			cache : false,
			type : "GET",
			contentType : "application/json",
			success : function(result) {
				if (result.sucesso) {
					window.app.notifyInfo("Listando:", JSON
							.stringify(result.mensagem));
					var mappedObjects = $.map(result.mensagem, function(item) {
						return new ListarSubproblemasResponse(item);
					});
					self.subproblemas(mappedObjects);
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

	self.cadastrarSubproblema = function(form) {
		var request = {
			nome : form.nome.value,
			problemaId : form.problemaId.value
		};
		window.app.notifyDebug("Incluindo...", JSON.stringify(ko
				.toJSON(request)));
		$.ajax("/smal/json/subproblema/cadastrarSubproblema", {
			cache : false,
			type : "POST",
			contentType : "application/json",
			data : ko.toJSON(request),
			success : function(result) {
				if (result.sucesso) {
					window.app.notifyInfo("Incluido:", JSON.stringify(result));
					self.listarSubproblemas();
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

	self.alterarSubproblema = function(subproblema) {
		var request = {
			id : subproblema.id,
			nome : subproblema.nome,
			problemaId : subproblema.problemaId
		};
		window.app.notifyDebug("Alterando...", JSON.stringify(ko
				.toJSON(request)));
		$.ajax("/smal/json/subproblema/alterarSubproblema", {
			cache : false,
			type : "POST",
			contentType : "application/json",
			data : ko.toJSON(request),
			success : function(result) {
				if (result.sucesso) {
					window.app.notifyInfo("Alterado:", JSON.stringify(result));
					self.listarSubproblemas();
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

	self.excluirSubproblema = function(subproblema) {
		var request = {
			id : subproblema.id
		};
		window.app.notifyDebug("Excluindo...", JSON.stringify(ko
				.toJSON(request)));
		$.ajax("/smal/json/subproblema/excluirSubproblema", {
			cache : false,
			type : "POST",
			contentType : "application/json",
			data : ko.toJSON(request),
			success : function(result) {
				if (result.sucesso) {
					window.app.notifyInfo("Excluido:", JSON.stringify(result));
					self.listarSubproblemas();
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

function ListarSubproblemasResponse(data) {
	this.id = ko.observable(data.id);
	this.nome = ko.observable(data.nome);
	this.problemaId = ko.observable(data.problemaId);
};
