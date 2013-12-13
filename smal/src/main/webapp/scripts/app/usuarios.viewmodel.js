var ticket = { name: "pedromontemor", tipo: "Aluno"};
window.app.viewmodel = (function (datacontext) {
    var self = this;
    this.usuarios = ko.observableArray([ticket, ticket, ticket, ticket, ticket, ticket, ticket, ticket, ticket, ticket, ticket, ticket, ticket, ticket, ticket, ticket, ticket, ticket]);
})(window.app.datacontext);
