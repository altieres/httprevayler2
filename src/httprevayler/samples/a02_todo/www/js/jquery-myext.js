$.extend({
	postJSON: function(url, data, success) {
		$.ajax({
			  url: url,
			  type: "POST",
			  dataType: "json",
			  contentType: "application/json",
			  data: data,
			  success: success
		});
	},
	deleteJSON: function(url, data, success) {
		$.ajax({
			  url: url,
			  type: "DELETE",
			  dataType: "json",
			  contentType: "application/json",
			  data: data,
			  success: success
		});
	}, 
});
