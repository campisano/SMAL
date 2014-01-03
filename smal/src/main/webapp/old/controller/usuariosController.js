function UsuariosViewModel() {
	// INTERNAL DATA
	var self = this;

	// BIND DATA
	self.usuarios = ko.observableArray([]);

	// ==============
	// CRUD FUNCTIONS
	// ==============

	// incluir
	self.incluir = function(usuario) {
		self.notificar("Incluindo...", JSON.stringify(ko.toJSON(usuario)));
		$.ajax("/smal/json/usuario/incluir", {
			cache : false,
			type : "POST",
			contentType : "application/json",
			data : ko.toJSON(usuario),
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
		$.ajax("/smal/json/usuario/obterTodos", {
			cache : false,
			type : "GET",
			contentType : "application/json",
			success : function(result) {
				if (result.sucesso) {
					var mappedUsuarios = $.map(result.mensagem, function(item) {
						return new Usuario(item);
					});
					self.usuarios(mappedUsuarios);
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
	self.alterar = function(usuario) {
		self.notificar("Alterando...", JSON.stringify(ko.toJSON(usuario)));
		$.ajax("/smal/json/usuario/alterar", {
			cache : false,
			type : "POST",
			contentType : "application/json",
			data : ko.toJSON(usuario),
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
	self.excluir = function(usuario) {
		self.notificar("Excluindo...", JSON.stringify(ko.toJSON(usuario)));
		$.ajax("/smal/json/usuario/excluir", {
			cache : false,
			type : "POST",
			contentType : "application/json",
			data : ko.toJSON(usuario),
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

	// incluiUsuario
	self.incluiUsuario = function(form) {
		var data = {
			id : null,
			nome : form.nome.value,
			matricula : form.matricula.value
		};
		self.incluir(new Usuario(data));
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
