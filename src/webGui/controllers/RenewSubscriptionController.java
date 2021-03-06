
package webGui.controllers;

import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import core.guiUtilities.CpsRegEx;
import core.guiUtilities.NumberTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import webGui.models.RenewSubscriptionModel;

public class RenewSubscriptionController {
	private RenewSubscriptionModel model;
	private ValidationSupport validation = new ValidationSupport();

	public RenewSubscriptionController() {
		model = new RenewSubscriptionModel();
	}

	@FXML
	protected void initialize() {
		renewSubscriptionBTN.disableProperty().bind(validation.invalidProperty());
		validation.registerValidator(customerIDTF, Validator.createRegexValidator("Customer ID is Required", CpsRegEx.IntegerBetweenMinAndMaxLength, Severity.ERROR));
		validation.registerValidator(subscriptionIDTF, Validator.createRegexValidator("Subscription ID is Required", CpsRegEx.IntegerBetweenMinAndMaxLength, Severity.ERROR));

	}

	@FXML // fx:id="trackOrderStatusBTN"
	private Button renewSubscriptionBTN; // Value injected by FXMLLoader

	@FXML // fx:id="customerIDTF"
	private NumberTextField customerIDTF; // Value injected by FXMLLoader

	@FXML // fx:id="orderIDTF"
	private NumberTextField subscriptionIDTF; // Value injected by FXMLLoader

	@FXML
	public void RenewSubscription(ActionEvent event) {
		model.SendRenewSubscriptionRequestToServer(customerIDTF.getNumber(), subscriptionIDTF.getNumber());
	}
}
