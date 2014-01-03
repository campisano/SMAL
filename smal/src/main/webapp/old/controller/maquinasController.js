function MaquinasViewModel() {
	// INTERNAL DATA
	var self = this;

	// BIND DATA
	self.maquinas = ko.observableArray([]);

	// ==============
	// CRUD FUNCTIONS
	// ==============

	// cadastrarMaquina
	self.cadastrarMaquina = function(maquina) {
		self.notificar("Incluindo...", JSON.stringify(ko.toJSON(maquina)));
		$.ajax("/smal/json/maquina/cadastrarMaquina", {
			cache : false,
			type : "POST",
			contentType : "application/json",
			data : ko.toJSON(maquina),
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

	// listarMaquinas
	self.listaMaquinas = function(laboratorio) {
		var request = {
			laboratorioId : laboratorio.id
		};
		self.notificar("Listar máquinas de laboratório...", JSON.stringify(ko
				.toJSON(request)));
		$.ajax("/smal/json/maquina/listarMaquinas", {
			cache : false,
			type : "POST",
			contentType : "application/json",
			data : ko.toJSON(request),
			success : function(result) {
				if (result.sucesso) {
					var mappedMaquinas = $.map(result.mensagem, function(item) {
						return new Maquina(item);
					});
					self.maquinas(mappedMaquinas);
					self.notificar("Obtidos:", JSON.stringify(result));
				} else {
					self.notificar("Erro na obrenção:", result.mensagem);
				}
			},
			failure : function(result) {
				self.notificar("Erro na obtenção:", JSON.stringify(result));
			}
		});
	};

	// alterarMaquina
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

	// excluirMaquina
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

	// ==================
	// OTHER FUNCTIONS
	// ==================

	// actionCadastrarMaquina
	self.actionCadastrarMaquina = function(form) {
		var maquina = new Maquina({
			patrimonio : form.patrimonio.value,
			laboratorioId : form.laboratorioId.value,
			fila : form.fila.value,
			coluna : form.coluna.value
		});
		self.cadastrarMaquina(maquina);
	};

	// actionListarMaquinas
	self.actionListarMaquinas = function(form) {
		var laboratorio = new Laboratorio({
			id : form.laboratorioId.value
		});
		self.listaMaquinas(laboratorio);
	};

	// actionAlterarMaquina
	self.actionAlterarMaquina = function(maquina) {
		self.alterarMaquina(maquina);
	};

	// actionExcluirMaquina
	self.actionExcluirMaquina = function(maquina) {
		self.excluirMaquina(maquina);
	};

	// notificar
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
}
