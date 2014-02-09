function ProblemasViewModel(app) {
	var self = this;
	self.app = app;
	self.problemas = ko.observableArray([]);

	self.listarProblemas = function() {
		self.app.notifyDebug("Obtendo todos...", "");
		$.ajax("/smal/json/problema/listarProblemas", {
			cache : false,
			type : "GET",
			contentType : "application/json",
			success : function(result) {
				if (result.sucesso) {
					self.app.notifyInfo("Listando:", JSON
							.stringify(result.mensagem));
					var mappedObjects = $.map(result.mensagem, function(item) {
						return new ListarProblemasResponse(item);
					});
					self.problemas(mappedObjects);
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

	self.cadastrarProblema = function(form) {
		var request = {
			nome : form.nome.value
		};
		self.app
				.notifyDebug("Incluindo...", JSON.stringify(ko.toJSON(request)));
		$.ajax("/smal/json/problema/cadastrarProblema", {
			cache : false,
			type : "POST",
			contentType : "application/json",
			data : ko.toJSON(request),
			success : function(result) {
				if (result.sucesso) {
					self.app.notifyInfo("Incluido:", JSON.stringify(result));
					self.listarProblemas();
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

	self.alterarProblema = function(problema) {
		var request = {
			id : problema.id,
			nome : problema.nome
		};
		self.app
				.notifyDebug("Alterando...", JSON.stringify(ko.toJSON(request)));
		$.ajax("/smal/json/problema/alterarProblema",
				{
					cache : false,
					type : "POST",
					contentType : "application/json",
					data : ko.toJSON(request),
					success : function(result) {
						if (result.sucesso) {
							self.app.notifyInfo("Alterado:", JSON
									.stringify(result));
							self.listarProblemas();
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

	self.excluirProblema = function(problema) {
		var request = {
			id : problema.id
		};
		self.app
				.notifyDebug("Excluindo...", JSON.stringify(ko.toJSON(request)));
		$.ajax("/smal/json/problema/excluirProblema", {
			cache : false,
			type : "POST",
			contentType : "application/json",
			data : ko.toJSON(request),
			success : function(result) {
				if (result.sucesso) {
					self.app.notifyInfo("Excluido:", JSON.stringify(result));
					self.listarProblemas();
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

	self.obterProblemasSelectOptions = function() {
		var arr_data =  self.problemas();
		var arr_select_opts = [];

		for(var i = 0; i <arr_data.length; ++i) {
			arr_select_opts.push( { id: arr_data[i].id(), nome: arr_data[i].nome()});
		}
		
		return arr_select_opts;
	};
};

function ListarProblemasResponse(data) {
	this.id = ko.observable(data.id);
	this.nome = ko.observable(data.nome);
};
