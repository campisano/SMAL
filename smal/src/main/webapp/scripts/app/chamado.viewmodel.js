window.app = window.app || {};
var chamado = {
	username : "",
	description : ""
};
window.app.viewmodel = function() {
	var self = this;
	this.chamados = ko.observableArray();
	this.newChamadoDescription = ko.observable();

	this.inserirAtendimento = function() {
		self.chamados.push({
			username : "pedromontemor",
			description : self.newChamadoDescription()
		});
	};
};
