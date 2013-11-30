function Usuario(data) {
	this.id = ko.observable(data.id);
	this.nome = ko.observable(data.nome);
	this.matricula = ko.observable(data.matricula);
}
