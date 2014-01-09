function SubproblemasViewModel(app) {
	var self = this;
	self.app = app;
	self.subproblemas = ko.observableArray([]);

	self.listarSubproblemas = function() {
		self.app.notifyDebug("Obtendo todos...", "");
		$.ajax("/smal/json/subproblema/listarSubproblemas", {
			cache : false,
			type : "GET",
			contentType : "application/json",
			success : function(result) {
				if (result.sucesso) {
					self.app.notifyInfo("Listando:", JSON
							.stringify(result.mensagem));
					var mappedObjects = $.map(result.mensagem, function(item) {
						return new ListarSubproblemasResponse(item);
					});
					self.subproblemas(mappedObjects);
				} else {
					self.app.notifyError("Erro na obtenção:", result.mensagem);
				}
			},
			failure : function(result) {
				self.app.notifyError("Erro na requisição:", JSON
						.stringify(result));
			}
		});
	};

	self.cadastrarSubproblema = function(form) {
		var request = {
			nome : form.nome.value,
			problemaId : form.problemaId.value
		};
		self.app
				.notifyDebug("Incluindo...", JSON.stringify(ko.toJSON(request)));
		$.ajax("/smal/json/subproblema/cadastrarSubproblema", {
			cache : false,
			type : "POST",
			contentType : "application/json",
			data : ko.toJSON(request),
			success : function(result) {
				if (result.sucesso) {
					self.app.notifyInfo("Incluido:", JSON.stringify(result));
					self.listarSubproblemas();
				} else {
					self.app.notifyError("Erro na inclusão:", result.mensagem);
				}
			},
			failure : function(result) {
				self.app.notifyError("Erro na inclusão:", JSON
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
		self.app
				.notifyDebug("Alterando...", JSON.stringify(ko.toJSON(request)));
		$.ajax("/smal/json/subproblema/alterarSubproblema",
				{
					cache : false,
					type : "POST",
					contentType : "application/json",
					data : ko.toJSON(request),
					success : function(result) {
						if (result.sucesso) {
							self.app.notifyInfo("Alterado:", JSON
									.stringify(result));
							self.listarSubproblemas();
						} else {
							self.app.notifyError("Erro na alteração:",
									result.mensagem);
						}
					},
					failure : function(result) {
						self.app.notifyError("Erro na alteraçaõ:", JSON
								.stringify(result));
					}
				});
	};

	self.excluirSubproblema = function(subproblema) {
		var request = {
			id : subproblema.id
		};
		self.app
				.notifyDebug("Excluindo...", JSON.stringify(ko.toJSON(request)));
		$.ajax("/smal/json/subproblema/excluirSubproblema", {
			cache : false,
			type : "POST",
			contentType : "application/json",
			data : ko.toJSON(request),
			success : function(result) {
				if (result.sucesso) {
					self.app.notifyInfo("Excluido:", JSON.stringify(result));
					self.listarSubproblemas();
				} else {
					self.app.notifyError("Erro na exclusão:", result.mensagem);
				}
			},
			failure : function(result) {
				self.app.notifyError("Erro na exclusão:", JSON
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
