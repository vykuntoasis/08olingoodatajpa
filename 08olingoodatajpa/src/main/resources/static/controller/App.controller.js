sap.ui.define([
	"sap/ui/core/mvc/Controller",
	"sap/m/MessageBox",
	"sap/m/MessageToast",
	"dorimon/util/service"
], function(Controller, MessageBox, MessageToast, Service){
	return Controller.extend("dorimon.controller.App",{
		onInit: function(){
			//Create connectivity to the Model object - odata
			var oDataModel = new sap.ui.model.odata.v2.ODataModel("/anubhav.svc");
			this.getView().setModel(oDataModel);
		}
	})
});