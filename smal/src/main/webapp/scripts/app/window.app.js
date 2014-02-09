var error_verbose = 1;
var warning_verbose = 2;
var info_verbose = 3;
var debug_verbose = 4;

function WindowApp() {
	var self = this;

	self.username = ko.observable("Guest");
	self.verbose = debug_verbose;

	self.notifyError = function(title, mensagem) {
		self.notify(title, mensagem, error_verbose);
	};

	self.notifyDebug = function(title, mensagem) {
		self.notify(title, mensagem, debug_verbose);
	};

	self.notifyInfo = function(title, mensagem) {
		self.notify(title, mensagem, info_verbose);
	};

	self.notifyWarning = function(title, mensagem) {
		self.notify(title, mensagem, warning_verbose);
	};

	self.notify = function(title, mensagem, verbose) {
		if (verbose <= self.verbose) {
			$.notific8(mensagem, {
				life : 5000,
				heading : title,
				theme : "ebony",
				horizontalEdge : "bottom",
				verticalEdge : "right",
				zindex : 1500
			});
		}
		;
	};

	self.refreshPage = function() {
		if (location.pathname != '/smal/index.html') {
			var preload = $('#preload');
			var content = $("#content");
			preload.addClass('in');
			setTimeout(function() {
				content.html($.ajax({
					type : "GET",
					url : location.pathname,
					async : false,
					cache : false
				}).responseText);
				preload.removeClass('in');
			}, 800);
		} else {
			self.changePage('/smal/chamados.html', undefined, undefined);
		}
	};

	self.changePage = function(url, text, image) {
		if (url != '/smal/index.html') {
			var content = $("#content");
			var preload = $('#preload');
			if (image != undefined)
				preload.find("img").attr('src', image);
			if (text != undefined)
				preload.find('h1').text(text);
			preload.addClass('in');
			setTimeout(function() {
				content.html($.ajax({
					type : "GET",
					url : url,
					async : false,
					cache : false
				}).responseText);
				preload.removeClass('in');
			}, 800);
		}
	};
};
