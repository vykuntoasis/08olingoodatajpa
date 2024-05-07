sap.ui.define([
	"sap/ui/core/mvc/Controller",
	"sap/m/MessageBox",
	"sap/m/MessageToast",
	"dorimon/util/service",
	"sap/m/Dialog",
	"sap/ui/layout/form/SimpleForm",
	"sap/m/Label",
	"sap/m/Input",
	"sap/m/Button",
	"sap/m/ButtonType",
	"sap/m/MessageBox"
], function(Controller, MessageBox, MessageToast, Service, Dialog,SimpleForm, Label, Input, Button, ButtonType, MessageBox){
	return Controller.extend("dorimon.controller.OData",{
		onInit: function(){
			
		},
		onSelect: function(oEvent){
			var oSelectedItem = oEvent.getParameter("listItem");
			var dataPath = oSelectedItem.getBindingContextPath();
			var data = this.getView().getModel().getProperty(dataPath);
			console.log(data);
		},
		oDefaultDialog : null,
		onOpen: function(){
			if (!this.oDefaultDialog) {
				this.oDefaultDialog = new Dialog({
					title: "Create Vendor",
					content: new SimpleForm({
						content: [
							new Label({text: "Company Name"}),
							new Input({value : "Anubhav Trainings"}),
							new Label({text: "Contact Person"}),
							new Input({value : "Minakshi"}),
							new Label({text: "First Name"}),
							new Input({value : "Anjali"}),
							new Label({text: "Last Name"}),
							new Input({value : "Sharma"}),
							new Label({text: "Website"}),
							new Input({value : "www.anubhavtrainings.com"}),
							new Label({text: "Email"}),
							new Input({value : "contact@anubhavtrainings.com"})							
						]
					}),
					beginButton: new Button({
						type: ButtonType.Emphasized,
						text: "Save",
						press: function (oEvent) {
							var payload = {
										    "CompanyName": oEvent.getSource().getParent().getAggregation("content")[0].getContent()[1].getValue(),
										    "ContactPerson": oEvent.getSource().getParent().getAggregation("content")[0].getContent()[3].getValue(),
										    "FirstName": oEvent.getSource().getParent().getAggregation("content")[0].getContent()[5].getValue(),
										    "LastName": oEvent.getSource().getParent().getAggregation("content")[0].getContent()[7].getValue(),
										    "Website": oEvent.getSource().getParent().getAggregation("content")[0].getContent()[9].getValue(),
										    "Email": oEvent.getSource().getParent().getAggregation("content")[0].getContent()[11].getValue(),
										    "Status": "A",
										    "RegDate": new Date()
										};
							var oDataModel = this.getView().getModel();
							oDataModel.create("/VendorSet", payload,{
								success: function(data){
									MessageBox.confirm("Hey Amigo! Data has been saved now " + data.Code);
								}
							});
							this.oDefaultDialog.close();
						}.bind(this)
					}),
					endButton: new Button({
						text: "Close",
						press: function () {
							this.oDefaultDialog.close();
						}.bind(this)
					})
				});

				// to get access to the controller's model
				this.getView().addDependent(this.oDefaultDialog);
			}

			this.oDefaultDialog.open();
		}
	});	
});