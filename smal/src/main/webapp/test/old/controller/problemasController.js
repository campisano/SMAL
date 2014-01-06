function ProblemasViewModel() {
	// INTERNAL DATA
	var self = this;

	// BIND DATA
	self.problemas = ko.observableArray([]);
	self.nomeNovoProblema = ko.observable();

	// ==============
	// CRUD FUNCTIONS
	// ==============

	// incluir
	self.incluir = function(problema) {
		self.notificar("Incluindo...", JSON.stringify(ko.toJSON(problema)));
		$.ajax("/smal/json/problema/incluir", {
			cache : false,
			type : "POST",
			contentType : "application/json",
			data : ko.toJSON(problema),
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
		$.ajax("/smal/json/problema/obterTodos", {
			cache : false,
			type : "GET",
			contentType : "application/json",
			success : function(result) {
				if (result.sucesso) {
					var mappedProblemas = $.map(result.mensagem, function(
							item) {
						return new Problema(item);
					});
					self.problemas(mappedProblemas);
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
	self.alterar = function(problema) {
		self.notificar("Alterando...", JSON.stringify(ko.toJSON(problema)));
		$.ajax("/smal/json/problema/alterar", {
			cache : false,
			type : "POST",
			contentType : "application/json",
			data : ko.toJSON(problema),
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
	self.excluir = function(problema) {
		self.notificar("Excluindo...", JSON.stringify(ko.toJSON(problema)));
		$.ajax("/smal/json/problema/excluir", {
			cache : false,
			type : "POST",
			contentType : "application/json",
			data : ko.toJSON(problema),
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

	// incluiProblema

	self.incluiProblema = function(form) {
		var data = {
			id : null,
			nome : form.nome.value
		};
		self.incluir(new Problema(data));
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
