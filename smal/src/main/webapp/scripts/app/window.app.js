var error_verbose = 1;
var warning_verbose = 2;
var info_verbose = 3;
var debug_verbose = 4;

function WindowApp() {
	var self = this;

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
};
