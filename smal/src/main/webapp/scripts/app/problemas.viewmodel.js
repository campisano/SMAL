var problema = { name: "" };
window.app.viewmodel = (function (datacontext) {
    var self = this;
    this.problemas = ko.observableArray([problema,problema,problema]);
})(window.app.datacontext);
