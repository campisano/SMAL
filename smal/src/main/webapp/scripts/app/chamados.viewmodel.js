function ChamadosViewModel(app) {
	var self = this;
	self.app = app;
	self.chamados = ko.observableArray([]);

	self.listarChamados = function() {
		self.app.notifyDebug("Listar chamados...", "");
		$.ajax("/smal/json/chamado/listarChamados", {
			cache : false,
			type : "GET",
			contentType : "application/json",
			success : function(result) {
				if (result.sucesso) {
					self.app.notifyInfo("Listando:", JSON
							.stringify(result.mensagem));
					var mappedChamados = $.map(result.mensagem, function(item) {
						return new ListarChamadosResponseAndDesignar_Fechar(
								item);
					});
					self.chamados(mappedChamados);
				} else {
					self.app.notifyError("Erro na obtenção:", result.mensagem);
				}
			},
			failure : function(result) {
				self.app.notifyError("Erro na requisição:", JSON
						.stringify(result));
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
		self.app.notifyDebug("Incluindo...", JSON.stringify(ko
				.toJSON(abrirChamadoRequest)));
		$.ajax("/smal/json/chamado/abrirChamado", {
			cache : false,
			type : "POST",
			contentType : "application/json",
			data : ko.toJSON(abrirChamadoRequest),
			success : function(result) {
				if (result.sucesso) {
					self.app.notifyInfo("Incluido:", JSON.stringify(result));
					self.listarChamados();
				} else {
					self.app.notifyError("Erro na inclusão:", result.mensagem);
				}
			},
			failure : function(result) {
				self.app.notifyError("Erro na requisição:", JSON
						.stringify(result));
			}
		});
	};

	self.designarChamado = function(form) {
		var designarChamadoRequest = {
			protocolo : form.protocolo,
			atendenteId : form.atendenteInput
		};
		self.app.notifyDebug("alterando...", JSON.stringify(ko
				.toJSON(designarChamadoRequest)));
		$.ajax("/smal/json/chamado/designarChamado",
				{
					cache : false,
					type : "POST",
					contentType : "application/json",
					data : ko.toJSON(designarChamadoRequest),
					success : function(result) {
						if (result.sucesso) {
							self.app.notifyInfo("Alterado:", JSON
									.stringify(result));
							self.listarChamados();
						} else {
							self.app.notifyError("Erro na alteração:",
									result.mensagem);
						}
					},
					failure : function(result) {
						self.app.notifyError("Erro na requisição:", JSON
								.stringify(result));
					}
				});
	};

	self.fecharChamado = function(form) {
		var fecharChamadoRequest = {
			protocolo : form.protocolo,
			exito : (ko.utils.unwrapObservable(form.statusInput) == 3) ? true
					: false
		};
		self.app.notifyDebug("Alterando...", JSON.stringify(ko
				.toJSON(fecharChamadoRequest)));
		$.ajax("/smal/json/chamado/fecharChamado",
				{
					cache : false,
					type : "POST",
					contentType : "application/json",
					data : ko.toJSON(fecharChamadoRequest),
					success : function(result) {
						if (result.sucesso) {
							self.app.notifyInfo("Alterando:", JSON
									.stringify(result));
							self.listarChamados();
						} else {
							self.app.notifyError("Erro na alteração:",
									result.mensagem);
						}
					},
					failure : function(result) {
						self.app.notifyError("Erro na requisição:", JSON
								.stringify(result));
					}
				});
	};
};

function ListarChamadosResponseAndDesignar_Fechar(data) {
	this.protocolo = ko.observable(data.protocolo);
	this.data_hora_abertura = ko.observable(new Date(data.data_hora_abertura));
	this.data_hora_fechamento = ko
			.observable((data.data_hora_fechamento != null) ? new Date(
					data.data_hora_fechamento) : "");
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

	// Designar_Fechar
	this.atendenteInput = ko.observable();
	this.statusInput = ko.observable();
};
