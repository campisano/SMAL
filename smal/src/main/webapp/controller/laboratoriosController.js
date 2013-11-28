function LaboratoriosViewModel() {
	// INTERNAL DATA
	var self = this;

	// BIND DATA
	self.laboratorios = ko.observableArray([]);
	self.nomeNovoLaboratorio = ko.observable();

	// ==============
	// CRUD FUNCTIONS
	// ==============

	// incluir
	self.incluir = function(laboratorio) {
		self.notificar("Incluindo...", JSON.stringify(ko.toJSON(laboratorio)));
		$.ajax("/smal/json/laboratorio/incluir", {
			cache : false,
			type : "POST",
			contentType : "application/json",
			data : ko.toJSON(laboratorio),
			success : function(result) {
				if (result.sucesso) {
					self.notificar("Incluido:", JSON.stringify(result));
					self.obterTodos();
				} else {
					self.notificar("Erro na inclusão:", result.mensagem);
				}
			},
			failure : function(result) {
				self.notificar("Erro na inclusão:", JSON.stringify(result));
			}
		});
	};

	// obterTodos
	self.obterTodos = function() {
		self.notificar("Obtendo todos...", "");
		$.ajax("/smal/json/laboratorio/obterTodos", {
			cache : false,
			type : "GET",
			contentType : "application/json",
			success : function(result) {
				if (result.sucesso) {
					var mappedLaboratorios = $.map(result.mensagem, function(
							item) {
						return new Laboratorio(item);
					});
					self.laboratorios(mappedLaboratorios);
					self.notificar("Obtidos:", JSON.stringify(result));
				} else {
					self.notificar("Erro na obrenção:", result.mensagem);
				}
			},
			failure : function(result) {
				self.notificar("Erro na obrenção:", JSON.stringify(result));
				self.obterTodos();
			}
		});
	};

	// alterar
	self.alterar = function(laboratorio) {
		self.notificar("Alterando...", JSON.stringify(ko.toJSON(laboratorio)));
		$.ajax("/smal/json/laboratorio/alterar", {
			cache : false,
			type : "POST",
			contentType : "application/json",
			data : ko.toJSON(laboratorio),
			success : function(result) {
				if (result.sucesso) {
					self.notificar("Alterado:", JSON.stringify(result));
					self.obterTodos();
				} else {
					self.notificar("Erro na alteração:", result.mensagem);
				}
			},
			failure : function(result) {
				self.notificar("Erro na alteraçaõ:", JSON.stringify(result));
				self.obterTodos();
			}
		});
	};

	// excluir
	self.excluir = function(laboratorio) {
		self.notificar("Excluindo...", JSON.stringify(ko.toJSON(laboratorio)));
		$.ajax("/smal/json/laboratorio/excluir", {
			cache : false,
			type : "POST",
			contentType : "application/json",
			data : ko.toJSON(laboratorio),
			success : function(result) {
				if (result.sucesso) {
					self.notificar("Excluido:", JSON.stringify(result));
					self.obterTodos();
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

	// incluiLaboratorio

	self.incluiLaboratorio = function(form) {
		var data = {
			id : null,
			nome : form.nome.value
		};
		self.incluir(new Laboratorio(data));
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

	self.obterTodos();
}
