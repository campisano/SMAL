function MaquinasViewModel() {
	// INTERNAL DATA
	var self = this;

	// BIND DATA
	self.laboratorio = ko.observable();
	self.maquinas = ko.observableArray([]);

	// ==============
	// CRUD FUNCTIONS
	// ==============

	// cadastrarMaquina
	self.incluir = function(maquina) {
		self.notificar("Incluindo...", JSON.stringify(ko.toJSON(maquina)));
		$.ajax("/smal/json/maquina/cadastrarMaquina", {
			cache : false,
			type : "POST",
			contentType : "application/json",
			data : ko.toJSON(maquina),
			success : function(result) {
				if (result.sucesso) {
					self.notificar("Incluido:", JSON.stringify(result));
				} else {
					self.notificar("Erro na inclusão:", result.mensagem);
				}
			},
			failure : function(result) {
				self.notificar("Erro na inclusão:", JSON.stringify(result));
			}
		});
	};

	// listarMaquinas
	self.listar = function(laboratorio) {
		self.notificar("Listar máquinas de laboratório...", "");
		$.ajax("/smal/json/maquina/listarMaquinas", {
			cache : false,
			type : "POST",
			contentType : "application/json",
			data : ko.toJSON(laboratorio),
			success : function(result) {
				if (result.sucesso) {
					var mappedMaquinas = $.map(result.mensagem, function(item) {
						return new Maquina(item);
					});
					self.maquinas(mappedMaquinas);
					self.notificar("Obtidos:", JSON.stringify(result));
				} else {
					self.notificar("Erro na obrenção:", result.mensagem);
				}
			},
			failure : function(result) {
				self.notificar("Erro na obtenção:", JSON.stringify(result));
			}
		});
	};

	// ==================
	// OTHER FUNCTIONS
	// ==================

	// listaMaquinas
	self.listaMaquinas = function(form) {
		self.laboratorio = form.laboratorioId.value;
		var data = {
			laboratorioId : form.laboratorioId.value
		};
		self.listar(data);
	};

	// incluiMaquina
	self.incluiMaquina = function(form) {
		var data = {
			patrimonio : form.patrimonio.value,
			laboratorioId : form.laboratorioId.value,
			fila : form.fila.value,
			coluna : form.coluna.value
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
}
