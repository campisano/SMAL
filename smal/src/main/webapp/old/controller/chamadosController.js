function ChamadosViewModel() {
	// INTERNAL DATA
	var self = this;

	// BIND DATA
	self.chamados = ko.observableArray([]);
	self.nomeNovoChamado = ko.observable();

	// ==============
	// CRUD FUNCTIONS
	// ==============

	// incluir
	self.incluir = function(chamado) {
		self.notificar("Incluindo...", JSON.stringify(ko.toJSON(chamado)));
		$.ajax("/smal/json/chamado/incluir", {
			cache : false,
			type : "POST",
			contentType : "application/json",
			data : ko.toJSON(chamado),
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
		$.ajax("/smal/json/chamado/obterTodos", {
			cache : false,
			type : "GET",
			contentType : "application/json",
			success : function(result) {
				if (result.sucesso) {
					var mappedChamados = $.map(result.mensagem, function(
							item) {
						return new Chamado(item);
					});
					self.chamados(mappedChamados);
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
	self.alterar = function(chamado) {
		self.notificar("Alterando...", JSON.stringify(ko.toJSON(chamado)));
		$.ajax("/smal/json/chamado/alterar", {
			cache : false,
			type : "POST",
			contentType : "application/json",
			data : ko.toJSON(chamado),
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
	self.excluir = function(chamado) {
		self.notificar("Excluindo...", JSON.stringify(ko.toJSON(chamado)));
		$.ajax("/smal/json/chamado/excluir", {
			cache : false,
			type : "POST",
			contentType : "application/json",
			data : ko.toJSON(chamado),
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

	// incluiChamado

	self.incluiChamado = function(form) {
		var data = {
			id : null,
			descricao : form.descricao.value
		};
		self.incluir(new Chamado(data));
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
