function Maquina(data) {
	this.id = ko.observable(data.id);
	this.patrimonio = ko.observable(data.patrimonio);
	this.laboratorioId = ko.observable(data.laboratorioId);
	this.fila = ko.observable(data.fila);
	this.coluna = ko.observable(data.coluna);
}
