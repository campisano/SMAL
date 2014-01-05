window.app = window.app || {};
window.app.viewmodel = new MaquinasViewModel();

function MaquinasViewModel() {
    var self = this;

	self.maquinas = ko.observableArray([]);
	
	self.listarMaquinas = function(form) {
		var listarMaquinasRequest = {
				laboratorioId : form.laboratorioId.value
			};
		self.notificar("Listar máquinas de laboratório...", JSON.stringify(ko.toJSON(listarMaquinasRequest)));
		$.ajax("/smal/json/maquina/listarMaquinas", {
			cache : false,
			type : "POST",
			contentType : "application/json",
			data : ko.toJSON(listarMaquinasRequest),
			success : function(result) {
				if (result.sucesso) {
					self.notificar("Listando:", JSON.stringify(result.mensagem));
					var mappedMaquinas = $.map(result.mensagem, function(
							item) {
						return new ObterTodosResponse(item);
					});
					self.maquinas(mappedMaquinas);
				} else {
					self.notificar("Erro na obtenção:", result.mensagem);
				}
			},
			failure : function(result) {
				self.notificar("Erro na requisição:", JSON.stringify(result));
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
		self.notificar("Incluindo...", JSON.stringify(ko.toJSON(cadastrarMaquinaRequest)));
		$.ajax("/smal/json/maquina/cadastrarMaquina", {
			cache : false,
			type : "POST",
			contentType : "application/json",
			data : ko.toJSON(cadastrarMaquinaRequest),
			success : function(result) {
				if (result.sucesso) {
					self.notificar("Incluido:", JSON.stringify(result));
				} else {
					self.notificar("Erro na inclusão:", result.mensagem);
				}
			},
			failure : function(result) {
				self.notificar("Erro na inclusão:", JSON.stringify(result));
			}
		});
	};

	self.alterarMaquina = function(maquina) {
		self.notificar("Alterando...", JSON.stringify(ko.toJSON(maquina)));
		$.ajax("/smal/json/maquina/alterarMaquina", {
			cache : false,
			type : "POST",
			contentType : "application/json",
			data : ko.toJSON(maquina),
			success : function(result) {
				if (result.sucesso) {
					self.notificar("Alterado:", JSON.stringify(result));
				} else {
					self.notificar("Erro na alteração:", result.mensagem);
				}
			},
			failure : function(result) {
				self.notificar("Erro na alteraçaõ:", JSON.stringify(result));
			}
		});
	};

	self.excluirMaquina = function(maquina) {
		var request = {
			maquinaId : maquina.id
		};
		self.notificar("Excluindo...", JSON.stringify(ko.toJSON(request)));
		$.ajax("/smal/json/maquina/excluirMaquina", {
			cache : false,
			type : "POST",
			contentType : "application/json",
			data : ko.toJSON(request),
			success : function(result) {
				if (result.sucesso) {
					self.notificar("Excluido:", JSON.stringify(result));
				} else {
					self.notificar("Erro na exclusão:", result.mensagem);
				}
			},
			failure : function(result) {
				self.notificar("Erro na exclusão:", JSON.stringify(result));
			}
		});
	};

	self.notificar = function(title, mensagem) {
		$.notific8(mensagem, {
			life : 5000,
			heading : title,
			theme : "ebony",
			horizontalEdge : "bottom",
			verticalEdge : "right",
			zindex : 1500
		});
	};
};

function ObterTodosResponse(data) {
	this.id = ko.observable(data.id);
	this.patrimonio = ko.observable(data.patrimonio);
	this.laboratorioId = ko.observable(data.laboratorioId);
	this.fila = ko.observable(data.fila);
	this.coluna = ko.observable(data.coluna);
};
