window.app = window.app || {};
window.app.viewmodel = new ChamadosViewModel();

function ChamadosViewModel() {
    var self = this;

	self.chamados = ko.observableArray([]);
	
	self.listarChamados = function() {
		$.ajax("/smal/json/chamado/listarChamados", {
			cache : false,
			type : "GET",
			contentType : "application/json",
			success : function(result) {
				if (result.sucesso) {
					self.notificar("Listando:", JSON.stringify(result.mensagem));
					var mappedChamados = $.map(result.mensagem, function(
							item) {
						return new ListarChamadosResponse(item);
					});
					self.chamados(mappedChamados);
				} else {
					self.notificar("Erro na obtenção:", result.mensagem);
				}
			},
			failure : function(result) {
				self.notificar("Erro na requisição:", JSON.stringify(result));
			}
		});
	};
	
	self.abrirChamado = function(form) {
		var abrirChamadoRequest = {
			matricula : form.matricula.value,
			maquinaId : form.maquinaId.value,
			subproblemaId : form.subproblemaId.value,
			descricao : form.descricao.value
		};
		self.notificar("Incluindo...", JSON.stringify(ko.toJSON(abrirChamadoRequest)));
		$.ajax("/smal/json/chamado/abrirChamado", {
			cache : false,
			type : "POST",
			contentType : "application/json",
			data : ko.toJSON(abrirChamadoRequest),
			success : function(result) {
				if (result.sucesso) {
					self.notificar("Incluido:", JSON.stringify(result));
					self.listarChamados();
				} else {
					self.notificar("Erro na inclusão:", result.mensagem);
				}
			},
			failure : function(result) {
				self.notificar("Erro na inclusão:", JSON.stringify(result));
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

function ListarChamadosResponse(data) {
	this.protocolo = ko.observable(data.protocolo);
	this.data_hora_abertura = ko.observable(new Date(data.data_hora_abertura));
	this.data_hora_fechamento = ko.observable((data.data_hora_fechamento != null) ? new Date(data.data_hora_fechamento) : "");
	this.descricao = ko.observable(data.descricao);
	this.abridorId = ko.observable(data.abridorId);
	this.atendenteId = ko.observable(data.atendenteId);
	this.designadorId = ko.observable(data.designadorId);
	switch (data.status) {
	case 1:
		this.status = ko.observable("aberto");
		break;
	case 2:
		this.status = ko.observable("em andamento");
		break;
	case 3:
		this.status = ko.observable("resolvido");
		break;
	case 4:
		this.status = ko.observable("não resolvido");
		break;
	default:
		this.status = ko.observable("erro");
	}
	this.subproblemaId = ko.observable(data.subproblemaId);
	this.maquinaId = ko.observable(data.maquinaId);
};
