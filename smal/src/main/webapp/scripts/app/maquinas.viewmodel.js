function MaquinasViewModel(app) {
	var self = this;
	self.app = app;
	self.maquinas = ko.observableArray([]);

	self.listarMaquinas = function(laboratorioId) {
		var listarMaquinasRequest = {
			laboratorioId : laboratorioId
		};
		self.app.notifyDebug("Listar máquinas de laboratório...", JSON
				.stringify(ko.toJSON(listarMaquinasRequest)));
		$.ajax("/smal/json/maquina/listarMaquinas", {
			cache : false,
			type : "POST",
			contentType : "application/json",
			data : ko.toJSON(listarMaquinasRequest),
			success : function(result) {
				if (result.sucesso) {
					self.app.notifyInfo("Listando:", JSON
							.stringify(result.mensagem));
					var mappedMaquinas = $.map(result.mensagem, function(item) {
						return new ListarMaquinasResponse(item);
					});
					self.maquinas(mappedMaquinas);
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

	self.cadastrarMaquina = function(form) {
		var cadastrarMaquinaRequest = {
			patrimonio : form.patrimonio.value,
			laboratorioId : form.laboratorioId.value,
			fila : form.fila.value,
			coluna : form.coluna.value
		};
		self.app.notifyDebug("Incluindo...", JSON.stringify(ko
				.toJSON(cadastrarMaquinaRequest)));
		$.ajax("/smal/json/maquina/cadastrarMaquina", {
			cache : false,
			type : "POST",
			contentType : "application/json",
			data : ko.toJSON(cadastrarMaquinaRequest),
			success : function(result) {
				if (result.sucesso) {
					self.app.notifyInfo("Incluido:", JSON.stringify(result));
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

	self.alterarMaquina = function(maquina) {
		self.app
				.notifyDebug("Alterando...", JSON.stringify(ko.toJSON(maquina)));
		$.ajax("/smal/json/maquina/alterarMaquina",
				{
					cache : false,
					type : "POST",
					contentType : "application/json",
					data : ko.toJSON(maquina),
					success : function(result) {
						if (result.sucesso) {
							self.app.notifyInfo("Alterado:", JSON
									.stringify(result));
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

	self.excluirMaquina = function(maquina) {
		var request = {
			maquinaId : maquina.id
		};
		self.app
				.notifyDebug("Excluindo...", JSON.stringify(ko.toJSON(request)));
		$.ajax("/smal/json/maquina/excluirMaquina", {
			cache : false,
			type : "POST",
			contentType : "application/json",
			data : ko.toJSON(request),
			success : function(result) {
				if (result.sucesso) {
					self.app.notifyInfo("Excluido:", JSON.stringify(result));
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

function ListarMaquinasResponse(data) {
	this.id = ko.observable(data.id);
	this.patrimonio = ko.observable(data.patrimonio);
	this.laboratorioId = ko.observable(data.laboratorioId);
	this.fila = ko.observable(data.fila);
	this.coluna = ko.observable(data.coluna);
};
