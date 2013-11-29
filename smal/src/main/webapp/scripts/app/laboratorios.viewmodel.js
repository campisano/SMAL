window.app = window.app || {};
window.app.viewmodel = function () {
    var self = this;
    this.laboratorios = ko.observableArray();
    window.app.datacontext.obterTodos(this.laboratorios);
    
};