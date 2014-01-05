window.app = window.app || {};
window.app.viewmodel = new UsuariosViewModel();

function UsuariosViewModel() {
	var self = this;

	self.usuarios = ko.observableArray([]);
	
	self.incluir = function(form) {
		var incluirRequest = {
				id : null,
				nome : form.nome.value,
				matricula : form.matricula.value
			};
		self.notificar("Incluindo...", JSON.stringify(ko.toJSON(incluirRequest)));
		$.ajax("/smal/json/usuario/incluir", {
			cache : false,
			type : "POST",
			contentType : "application/json",
			data : ko.toJSON(incluirRequest),
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

	self.obterTodos = function() {
		self.notificar("Obtendo todos...", "");
		$.ajax("/smal/json/usuario/obterTodos", {
			cache : false,
			type : "GET",
			contentType : "application/json",
			success : function(result) {
				if (result.sucesso) {
					var mappedUsuarios = $.map(result.mensagem, function(item) {
						return new ObterTodosResponse(item);
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
	this.nome = ko.observable(data.nome);
	this.matricula = ko.observable(data.matricula);
};
