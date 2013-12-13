window.app = window.app || {};
var ticket = {
	username: "",
	description: ""
};
window.app.viewmodel = function () {
    var self = this;
    this.tickets = ko.observableArray();
	this.newTicketDescription = ko.observable();
	
    this.inserirAtendimento = function() { 
		self.tickets.push({
			username: "pedromontemor",
			description: self.newTicketDescription()
		});
	}
};

