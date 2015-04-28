$(document).ready(function () {
	$.validator.addMethod(
			"regex",
			function(value, element, regexp) {
				var re = new RegExp(regexp);
				return this.optional(element) || re.test(value);
			},
			"Invalid format."
	);
	
	
	$(function() {
		if (strings['lang'] === 'fr') {
			$("#editComputerForm").validate({
				onkeyup : false,
				onfocusout : function(element) { $(element).valid(); },
				errorElement : "div",
				errorPlacement: function(error, element) {
					  if (element.attr("name") == "name") {
					    error.appendTo("div#errorName");
					  } else if (element.attr("name") == "introduced") {
						error.appendTo("div#errorIntroduced");
					  } else if (element.attr("name") == "discontinued") {
						error.appendTo("div#errorDiscontinued");
					  }
					}
				,
				rules : {
					"name" : {
						required : true,
						minlength : 2,
						regex : "^[a-zA-Z]"
					},
					"introduced" : {
						regex : "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[012])-\\d{4} (20|21|22|23|[01]\\d|\\d)(([:][0-5]\\d){1,2})(([:][0-5]\\d){1,2})$"
					},
					"discontinued" : {
						regex : "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[012])-\\d{4} (20|21|22|23|[01]\\d|\\d)(([:][0-5]\\d){1,2})(([:][0-5]\\d){1,2})$"
					}
				},
				messages : {
					"name" : {
						required : "The name is required.",
						minlength : "Name minimum length is 2.",
						regex : "Wrong name format."
					},
					"introduced" : {
						regex : "Bad format."
					},
					"discontinued" : {
						regex : "Bad format."
					}
				}
			});
			
			
			
		} else {
			$("#editComputerForm").validate({
				onkeyup : false,
				onfocusout : function(element) { $(element).valid(); },
				errorElement : "div",
				errorPlacement: function(error, element) {
					  if (element.attr("name") == "name") {
					    error.appendTo("div#errorName");
					  } else if (element.attr("name") == "introduced") {
						error.appendTo("div#errorIntroduced");
					  } else if (element.attr("name") == "discontinued") {
						error.appendTo("div#errorDiscontinued");
					  }
					}
				,
				rules : {
					"name" : {
						required : true,
						minlength : 2,
						regex : "^[a-zA-Z]"
					},
					"introduced" : {
						regex : "^\\d{4}-(0[1-9]|1[012])-((0[1-9]|[12][0-9]|3[01]) (20|21|22|23|[01]\\d|\\d)(([:][0-5]\\d){1,2})(([:][0-5]\\d){1,2}))$"
					},
					"discontinued" : {
						regex : "^\\d{4}-(0[1-9]|1[012])-((0[1-9]|[12][0-9]|3[01]) (20|21|22|23|[01]\\d|\\d)(([:][0-5]\\d){1,2})(([:][0-5]\\d){1,2}))$"
					}
				},
				messages : {
					"name" : {
						required : "The name is required.",
						minlength : "Name minimum length is 2.",
						regex : "Wrong name format."
					},
					"introduced" : {
						regex : "Bad format."
					},
					"discontinued" : {
						regex : "Bad format."
					}
				}
			});
		}
	
	});
	
	
	$(function() {
		if (strings['lang'] === 'fr') {
			$("#addComputerForm2").validate({
				onkeyup : false,
				onfocusout : function(element) { $(element).valid(); },
				errorElement : "div",
				errorPlacement: function(error, element) {
					  if (element.attr("name") == "name") {
					    error.appendTo("div#errorName");
					  } else if (element.attr("name") == "introduced") {
						error.appendTo("div#errorIntroduced");
					  } else if (element.attr("name") == "discontinued") {
						error.appendTo("div#errorDiscontinued");
					  }
					}
				,
				rules : {
					"name" : {
						required : true,
						minlength : 2,
						regex : "^[a-zA-Z]"
					},
					"introduced" : {
						regex : "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[012])-\\d{4} (20|21|22|23|[01]\\d|\\d)(([:][0-5]\\d){1,2})(([:][0-5]\\d){1,2})$"
					},
					"discontinued" : {
						regex : "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[012])-\\d{4} (20|21|22|23|[01]\\d|\\d)(([:][0-5]\\d){1,2})(([:][0-5]\\d){1,2})$"
					}
				},
				messages : {
					"name" : {
						required : "The name is required.",
						minlength : "Name minimum length is 2.",
						regex : "Wrong name format."
					},
					"introduced" : {
						regex : "Bad format."
					},
					"discontinued" : {
						regex : "Bad format."
					}
				},
		        success: function(label) {
		            label.addClass("success");
		        }
			});
			
		} else {
			$("#addComputerForm2").validate({
				onkeyup : false,
				onfocusout : function(element) { $(element).valid(); },
				errorElement : "div",
				errorPlacement: function(error, element) {
					  if (element.attr("name") == "name") {
					    error.appendTo("div#errorName");
					  } else if (element.attr("name") == "introduced") {
						error.appendTo("div#errorIntroduced");
					  } else if (element.attr("name") == "discontinued") {
						error.appendTo("div#errorDiscontinued");
					  }
					}
				,
				rules : {
					"name" : {
						required : true,
						minlength : 2,
						regex : "^[a-zA-Z]"
					},
					"introduced" : {
						regex : "^\\d{4}-(0[1-9]|1[012])-((0[1-9]|[12][0-9]|3[01]) (20|21|22|23|[01]\\d|\\d)(([:][0-5]\\d){1,2})(([:][0-5]\\d){1,2}))$"
					},
					"discontinued" : {
						regex : "^\\d{4}-(0[1-9]|1[012])-((0[1-9]|[12][0-9]|3[01]) (20|21|22|23|[01]\\d|\\d)(([:][0-5]\\d){1,2})(([:][0-5]\\d){1,2}))$"
					}
				},
				messages : {
					"name" : {
						required : "The name is required.",
						minlength : "Name minimum length is 2.",
						regex : "Wrong name format."
					},
					"introduced" : {
						regex : "Bad format."
					},
					"discontinued" : {
						regex : "Bad format."
					}
				},
		        success: function(label) {
		            label.addClass("success");
		        }
			});
		}
	
	});

	
});