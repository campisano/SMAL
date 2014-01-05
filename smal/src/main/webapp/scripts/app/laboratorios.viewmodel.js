window.app = window.app || {};
window.app.viewmodel = new LaboratoriosViewModel();

function Laboratorio(data) {
	this.id = ko.observable(data.id);
	this.nome = ko.observable(data.nome);
};

function LaboratoriosViewModel() {
    var self = this;

	self.laboratorios = ko.observableArray([]);
	
	self.obterTodos = function() {
		$.ajax("/smal/json/laboratorio/obterTodos", {
			cache : false,
			type : "GET",
			contentType : "application/json",
			success : function(result) {
				if (result.sucesso) {
					self.notificar("Listando:", JSON.stringify(result.mensagem));
					var mappedLaboratorios = $.map(result.mensagem, function(
							item) {
						return new Laboratorio(item);
					});
					self.laboratorios(mappedLaboratorios);
				} else {
					self.notificar("Erro na obtenção:", result.mensagem);
				}
			},
			failure : function(result) {
				self.notificar("Erro na requisição:", JSON.stringify(result));
			}
		});
	};
	
	self.incluir = function(form) {
		var incluirRequest = {
			nome : form.nome.value
		};
		self.notificar("Incluindo...", JSON.stringify(ko.toJSON(incluirRequest)));
		$.ajax("/smal/json/laboratorio/incluir", {
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
