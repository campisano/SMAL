var ticket = {};
window.app.viewmodel = (function (datacontext) {
    var self = this;
    this.tickets = ko.observableArray();
    window.app.datacontext.listarChamados(this.tickets);
})(window.app.datacontext);
