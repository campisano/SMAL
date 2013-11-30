function MaquinasViewModel() {
	// INTERNAL DATA
	var self = this;

	// BIND DATA
	self.maquinas = ko.observableArray([]);
	self.nomeNovoMaquina = ko.observable();

	// ==============
	// CRUD FUNCTIONS
	// ==============

	// incluir
	self.incluir = function(maquina) {
		self.notificar("Incluindo...", JSON.stringify(ko.toJSON(maquina)));
		$.ajax("/smal/json/maquina/incluir", {
			cache : false,
			type : "POST",
			contentType : "application/json",
			data : ko.toJSON(maquina),
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
		$.ajax("/smal/json/maquina/obterTodos", {
			cache : false,
			type : "GET",
			contentType : "application/json",
			success : function(result) {
				if (result.sucesso) {
					var mappedMaquinas = $.map(result.mensagem, function(
							item) {
						return new Maquina(item);
					});
					self.maquinas(mappedMaquinas);
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
	self.alterar = function(maquina) {
		self.notificar("Alterando...", JSON.stringify(ko.toJSON(maquina)));
		$.ajax("/smal/json/maquina/alterar", {
			cache : false,
			type : "POST",
			contentType : "application/json",
			data : ko.toJSON(maquina),
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
	self.excluir = function(maquina) {
		self.notificar("Excluindo...", JSON.stringify(ko.toJSON(maquina)));
		$.ajax("/smal/json/maquina/excluir", {
			cache : false,
			type : "POST",
			contentType : "application/json",
			data : ko.toJSON(maquina),
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

	// incluiMaquina

	self.incluiMaquina = function(form) {
		var data = {
			id : null,
			patrimonio : form.patrimonio.value
		};
		self.incluir(new Maquina(data));
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
