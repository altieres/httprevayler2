/**
 Usage: Just include this script after Marionette and Handlebars loading
 IF you use require.js add script to shim and describe it in the requirements
*/
(function(Handlebars, Marionette) {
 
	Marionette.Handlebars = {
		path: 'templates/',
		extension: '.handlebars'
	};
 
	Marionette.TemplateCache.prototype.loadTemplate = function(templateId) {
		var template, templateUrl;
		if (Handlebars.templates && Handlebars.templates[templateId]) {
			return '[precompiled]';
		}
		try{
		    template = Marionette.$(templateId).html();
		} catch(e) {}
		if (!template || template.length === 0) {
			templateUrl = Marionette.Handlebars.path + templateId + Marionette.Handlebars.extension;
			Marionette.$.ajax({
				url: templateUrl,
				success: function(data) {
					template = data;
				},
				async: false
			});
			if (!template || template.length === 0){
				throw "NoTemplateError - Could not find template: '" + templateUrl + "'";
			}
		}
		return template;
	};
 
	Marionette.TemplateCache.prototype.compileTemplate = function(rawTemplate) {
		if (Handlebars.templates && Handlebars.templates[templateId]) {
			return Handlebars.templates[templateId];
		}
		return Handlebars.compile(rawTemplate);
	};
 
}(Handlebars, Marionette));