package webGui.models;

import java.util.Date;

import core.customer.CustomerRequest;
import core.guiUtilities.ServerMessageHandler;
import webGui.util.CustomerRequestFactory;
import webGui.util.MockWebClientConnectionManager;

public class OrderFullMonthlySubscriptionModel {
	
	private MockWebClientConnectionManager connectionManager;
	
	public OrderFullMonthlySubscriptionModel(ServerMessageHandler controller) {
		connectionManager = MockWebClientConnectionManager.getInstance();
		connectionManager.addServerMessageListener(controller);
	}
	
	public void SendFullMonthlySubcriptionRequestToServer(int customerID, String liscencePlate, String email, Date startingDate){
		CustomerRequest request = CustomerRequestFactory.CreateOrderFullMonthlySubscriptionRequest(customerID, liscencePlate, email, startingDate);
		connectionManager.sendMessageToServer(request);
	}
}
