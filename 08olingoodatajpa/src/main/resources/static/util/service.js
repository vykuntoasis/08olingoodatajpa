sap.ui.define(
	["jquery.sap.global"],
	function(jQuery){
		return {
			callService: function(sUrl, sMethod, payload){
				return new Promise(function(resolve, reject){
					switch(sMethod.toUpperCase()){
						case "GET":
							jQuery.ajax(sUrl,{
								type: 'GET',
								success: function(data){
									resolve(data);
								},
								error: function(oError){
									reject(oError);
								}
							});
							break;
						case "POST":
							jQuery.ajax(sUrl,{
								type: 'POST',
								contentType: 'application/json',
								data: JSON.stringify(payload),
								success: function(data){
									resolve(data);
								},
								error: function(oError){
									reject(oError);
								}
							});
							break;
					}
				});
			}
		};
	}	
);