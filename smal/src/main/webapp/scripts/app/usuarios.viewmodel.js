window.app.viewmodel = new UsuariosViewModel();

function UsuariosViewModel() {
	var self = this;

	self.usuarios = ko.observableArray([]);

	self.incluir = function(form) {
		var incluirRequest = {
			nome : form.nome.value,
			matricula : form.matricula.value,
			tipo : form.tipo.value
		};
		window.app.notifyDebug("Incluindo...", JSON.stringify(ko
				.toJSON(incluirRequest)));
		$.ajax("/smal/json/usuario/incluir", {
			cache : false,
			type : "POST",
			contentType : "application/json",
			data : ko.toJSON(incluirRequest),
			success : function(result) {
				if (result.sucesso) {
					window.app.notifyInfo("Incluido:", JSON.stringify(result));
					self.obterTodos();
				} else {
					window.app
							.notifyError("Erro na inclusão:", result.mensagem);
				}
			},
			failure : function(result) {
				window.app.notifyError("Erro na requisição:", JSON
						.stringify(result));
			}
		});
	};

	self.obterTodos = function() {
		window.app.notifyDebug("Obtendo todos...", "");
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
					window.app.notifyInfo("Obtidos:", JSON.stringify(result));
				} else {
					window.app
							.notifyError("Erro na obrenção:", result.mensagem);
				}
			},
			failure : function(result) {
				window.app.notifyError("Erro na requisição:", JSON
						.stringify(result));
				self.obterTodos();
			}
		});
	};

	self.alterar = function(usuario) {
		var alterarRequest = {
			id : usuario.id,
			nome : usuario.nome,
			matricula : usuario.matricula
		};
		window.app.notifyDebug("Alterando...", JSON.stringify(ko
				.toJSON(alterarRequest)));
		$.ajax("/smal/json/usuario/alterar", {
			cache : false,
			type : "POST",
			contentType : "application/json",
			data : ko.toJSON(alterarRequest),
			success : function(result) {
				if (result.sucesso) {
					window.app.notifyInfo("Alterado:", JSON.stringify(result));
					self.obterTodos();
				} else {
					window.app.notifyError("Erro na alteração:",
							result.mensagem);
				}
			},
			failure : function(result) {
				window.app.notifyError("Erro na requisição:", JSON
						.stringify(result));
				self.obterTodos();
			}
		});
	};

	self.excluir = function(usuario) {
		var excluirRequest = {
			id : usuario.id
		};
		window.app.notifyDebug("Excluindo...", JSON.stringify(ko
				.toJSON(excluirRequest)));
		$.ajax("/smal/json/usuario/excluir", {
			cache : false,
			type : "POST",
			contentType : "application/json",
			data : ko.toJSON(excluirRequest),
			success : function(result) {
				if (result.sucesso) {
					window.app.notifyInfo("Excluido:", JSON.stringify(result));
					self.obterTodos();
				} else {
					window.app
							.notifyError("Erro na exclusão:", result.mensagem);
				}
			},
			failure : function(result) {
				window.app.notifyError("Erro na requisição:", JSON
						.stringify(result));
			}
		});
	};
};

function ObterTodosResponse(data) {
	this.id = ko.observable(data.id);
	this.nome = ko.observable(data.nome);
	this.matricula = ko.observable(data.matricula);
	switch (data.tipo) {
	case 1:
		this.tipo = ko.observable("administrador");
		break;
	case 2:
		this.tipo = ko.observable("técnico");
		break;
	default:
		this.tipo = ko.observable("usuário");
	}
};
